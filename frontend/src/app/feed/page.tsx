'use client';

import withAuth from '@/components/withAuth';
import PostCard from '@/components/PostCard';
import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import { useAuth } from '@/context/AuthContext';

interface Post {
  id: number;
  userId: number;
  type: 'TEXT' | 'IMAGE' | 'VIDEO';
  url?: string;
  caption: string;
  createdAt: string;
}

function FeedPage() {
  const { user } = useAuth();
  const { data: posts, isLoading, error, refetch } = useQuery<Post[]>({
    queryKey: ['feed', user?.sub],
    queryFn: async () => {
      // FIXME: use the actual user id from the user object
      const response = await axios.get(`http://localhost:8083/feed/1`);
      return response.data;
    },
    enabled: !!user,
  });

  if (isLoading) return <div>Loading feed...</div>;
  if (error) return <div>Error loading feed</div>;

  return (
    <div>
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold">Feed</h1>
        <button
          onClick={() => refetch()}
          className="px-4 py-2 font-bold text-white bg-blue-600 rounded-md hover:bg-blue-700"
        >
          Refresh
        </button>
      </div>
      <div>
        {posts?.map((post) => (
          <PostCard key={post.id} post={post} />
        ))}
      </div>
    </div>
  );
}

export default withAuth(FeedPage);
