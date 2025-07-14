'use client';

import { useContext } from 'react';

import { AuthContext } from '@/context/AuthContext';

import { Avatar, AvatarFallback } from '@/components/ui/avatar';
import { Badge } from '@/components/ui/badge';

export default function Header() {
  const { user } = useContext(AuthContext);

  return (
    <div className="relative bg-gradient-to-r from-blue-600 to-purple-600 rounded-2xl p-8 mb-8 text-white overflow-hidden">
      <div className="flex items-center space-x-6 mb-6 md:mb-0 z-10">
        <div className="relative">
          <Avatar className="h-24 w-24 border-4 border-white/20">
            <AvatarFallback className="text-2xl bg-white/20">
              {user?.username
                .split(' ')
                .map((n) => n[0])
                .join('')}
            </AvatarFallback>
          </Avatar>
        </div>

        <div>
          <h1 className="text-3xl font-bold mb-2">{user?.username}</h1>
          <p className="text-white/80 mb-2">{user?.email}</p>
          <Badge
            variant="secondary"
            className="bg-white/20 text-white border-white/30"
          >
            Miembro desde{' '}
            {/* {new Date(userData.joinDate).toLocaleDateString()} */}
          </Badge>
        </div>
      </div>
    </div>
  );
}
