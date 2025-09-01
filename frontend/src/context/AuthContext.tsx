'use client';

import { createContext, useContext, useState, useEffect, ReactNode } from 'react';
import { jwtDecode } from 'jwt-decode';

interface User {
  sub: string;
  iat: number;
  exp: number;
}

interface AuthContextType {
  user: User | null;
  token: string | null;
  login: (token: string) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<User | null>(null);
  const [token, setToken] = useState<string | null>(null);

  useEffect(() => {
    const storedToken = localStorage.getItem('jwt');
    if (storedToken) {
      try {
        const decodedUser: User = jwtDecode(storedToken);
        setUser(decodedUser);
        setToken(storedToken);
      } catch (error) {
        console.error('Invalid token');
        localStorage.removeItem('jwt');
      }
    }
  }, []);

  const login = (newToken: string) => {
    localStorage.setItem('jwt', newToken);
    try {
      const decodedUser: User = jwtDecode(newToken);
      setUser(decodedUser);
      setToken(newToken);
    } catch (error) {
      console.error('Invalid token');
    }
  };

  const logout = () => {
    localStorage.removeItem('jwt');
    setUser(null);
    setToken(null);
  };

  return (
    <AuthContext.Provider value={{ user, token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
