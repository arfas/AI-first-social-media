'use client';

import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';
import { useState } from 'react';
import { useAuth } from '@/context/AuthContext';

interface Comment {
  id: number;
  userId: number;
  contentId: number;
  text: string;
  createdAt: string;
}

interface CommentsModalProps {
  postId: number;
  onClose: () => void;
}

export default function CommentsModal({ postId, onClose }: CommentsModalProps) {
  const { token } = useAuth();
  const queryClient = useQueryClient();
  const [newComment, setNewComment] = useState('');

  const { data: comments, isLoading, error } = useQuery<Comment[]>({
    queryKey: ['comments', postId],
    queryFn: async () => {
      const response = await axios.get(`http://localhost:8083/comment/${postId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      return response.data;
    },
  });

  const addCommentMutation = useMutation({
    mutationFn: (text: string) => {
      return axios.post(`http://localhost:8083/comment/${postId}`, text, {
        headers: {
          'Content-Type': 'text/plain',
          Authorization: `Bearer ${token}`
        },
      });
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['comments', postId] });
      setNewComment('');
    },
  });

  const handleAddComment = (e: React.FormEvent) => {
    e.preventDefault();
    addCommentMutation.mutate(newComment);
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div className="bg-white p-6 rounded-lg w-full max-w-lg">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-bold">Comments</h2>
          <button onClick={onClose} className="text-2xl">&times;</button>
        </div>
        <div>
          {isLoading && <p>Loading comments...</p>}
          {error && <p>Error loading comments.</p>}
          {comments?.map(comment => (
            <div key={comment.id} className="border-b py-2">
              <p><strong>User {comment.userId}</strong></p>
              <p>{comment.text}</p>
              <p className="text-xs text-gray-500">{new Date(comment.createdAt).toLocaleString()}</p>
            </div>
          ))}
        </div>
        <form onSubmit={handleAddComment} className="mt-4">
          <textarea
            value={newComment}
            onChange={(e) => setNewComment(e.target.value)}
            className="w-full p-2 border rounded-md"
            placeholder="Add a comment..."
            rows={2}
          />
          <button
            type="submit"
            className="mt-2 px-4 py-2 bg-blue-600 text-white rounded-md"
            disabled={addCommentMutation.isPending}
          >
            {addCommentMutation.isPending ? 'Posting...' : 'Post'}
          </button>
        </form>
      </div>
    </div>
  );
}
