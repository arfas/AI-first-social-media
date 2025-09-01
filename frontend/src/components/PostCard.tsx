import { useMutation, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';
import { useAuth } from '@/context/AuthContext';
import { useState } from 'react';
import CommentsModal from './CommentsModal';

interface Post {
  id: number;
  userId: number;
  type: 'TEXT' | 'IMAGE' | 'VIDEO';
  url?: string;
  caption: string;
  createdAt: string;
}

interface PostCardProps {
  post: Post;
}

export default function PostCard({ post }: PostCardProps) {
  const [isCommentsModalOpen, setIsCommentsModalOpen] = useState(false);
  const { token } = useAuth();
  const queryClient = useQueryClient();

  const likeMutation = useMutation({
    mutationFn: () => {
      return axios.post(`http://localhost:8083/like/${post.id}`, {}, {
        headers: { Authorization: `Bearer ${token}` },
      });
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['feed'] });
    },
  });

  return (
    <div className="bg-white shadow-md rounded-lg p-4 my-4">
      <div className="flex items-center mb-4">
        <div className="font-bold">User {post.userId}</div>
      </div>
      {post.type === 'IMAGE' && post.url && (
        <img src={post.url} alt={post.caption} className="w-full rounded-lg" />
      )}
      {post.type === 'VIDEO' && post.url && (
        <video src={post.url} controls className="w-full rounded-lg" />
      )}
      <p className="my-4">{post.caption}</p>
      <div className="flex justify-between text-gray-500">
        <button
          onClick={() => likeMutation.mutate()}
          className="hover:text-blue-500"
          disabled={likeMutation.isPending}
        >
          {likeMutation.isPending ? 'Liking...' : 'Like'}
        </button>
        <span>0 Likes</span>
        <button onClick={() => setIsCommentsModalOpen(true)} className="hover:underline">
          View more comments
        </button>
      </div>
      {isCommentsModalOpen && (
        <CommentsModal postId={post.id} onClose={() => setIsCommentsModalOpen(false)} />
      )}
    </div>
  );
}
