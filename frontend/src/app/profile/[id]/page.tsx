'use client';

import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';
import { useParams } from 'next/navigation';
import withAuth from '@/components/withAuth';
import { useAuth } from '@/context/AuthContext';
import PostCard from '@/components/PostCard';

interface UserProfile {
  id: number;
  username: string;
  email: string;
  createdAt: string;
}

interface Post {
  id: number;
  userId: number;
  type: 'TEXT' | 'IMAGE' | 'VIDEO';
  url?: string;
  caption: string;
  createdAt: string;
}

function ProfilePage() {
  const params = useParams();
  const { id } = params;
  const { token } = useAuth();
  const queryClient = useQueryClient();

  const followMutation = useMutation({
    mutationFn: () => {
      return axios.post(`http://localhost:8083/follow/${id}`, {}, {
        headers: { Authorization: `Bearer ${token}` },
      });
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['profile', id] });
    },
  });

  const { data: profile, isLoading: isLoadingProfile } = useQuery<UserProfile>({
    queryKey: ['profile', id],
    queryFn: async () => {
      const response = await axios.get(`http://localhost:8083/profile/${id}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      return response.data;
    },
    enabled: !!id,
  });

  // This is not ideal, we should have an endpoint in content-service to get posts by user id
  const { data: posts, isLoading: isLoadingPosts } = useQuery<Post[]>({
    queryKey: ['posts', id],
    queryFn: async () => {
      const response = await axios.get(`http://localhost:8083/content`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      return response.data.filter((post: Post) => post.userId === Number(id));
    },
    enabled: !!id,
  });


  if (isLoadingProfile || isLoadingPosts) return <div>Loading profile...</div>;

  return (
    <div>
      <div className="p-4 bg-white rounded-lg shadow-md">
        <h1 className="text-2xl font-bold">{profile?.username}</h1>
        <p className="text-gray-500">Joined on {new Date(profile?.createdAt || '').toLocaleDateString()}</p>
        <div className="flex space-x-4 mt-4">
          <span>0 Followers</span>
          <span>0 Following</span>
        </div>
        <button
          onClick={() => followMutation.mutate()}
          className="mt-4 px-4 py-2 font-bold text-white bg-blue-600 rounded-md hover:bg-blue-700"
          disabled={followMutation.isPending}
        >
          {followMutation.isPending ? 'Following...' : 'Follow'}
        </button>
      </div>
      <div className="mt-8">
        <h2 className="text-xl font-bold">Posts</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-4">
          {posts?.map((post) => (
            <PostCard key={post.id} post={post} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default withAuth(ProfilePage);
