'use client';

import { createContext, PropsWithChildren } from 'react';
import { User } from '@/interfaces/User';

type AuthContextType = {
  user: User | null;
  token: string | null;
};

export const AuthContext = createContext<AuthContextType>({
  user: null,
  token: null,
});

export default function AuthProvider({
  children,
  user,
  token,
}: AuthContextType & PropsWithChildren) {
  return (
    <AuthContext.Provider value={{ user, token }}>
      {children}
    </AuthContext.Provider>
  );
}
