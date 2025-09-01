'use client';

import { useAuth } from '@/context/AuthContext';
import { useRouter } from 'next/navigation';
import { useEffect, ComponentType } from 'react';

const withAuth = <P extends object>(WrappedComponent: ComponentType<P>) => {
  const Wrapper = (props: P) => {
    const { user, token } = useAuth();
    const router = useRouter();

    useEffect(() => {
      if (!token) {
        router.push('/login');
      }
    }, [token, router]);

    if (!user) {
      return <div>Loading...</div>; // Or a spinner
    }

    return <WrappedComponent {...props} />;
  };

  return Wrapper;
};

export default withAuth;
