'use client';

import { createContext, PropsWithChildren } from 'react';
import { User } from '@/interfaces/User';

type AuthContextType = {
  user: User | null;
};

export const AuthContext = createContext<AuthContextType>({
  user: null,
});

export default function AuthProvider({
  children,
  user,
}: AuthContextType & PropsWithChildren) {
  return (
    <AuthContext.Provider value={{ user }}>{children}</AuthContext.Provider>
  );
}
