'use client';

import { BarChart3, Eye, Users } from 'lucide-react';
import Stat from './Stat';
import { useContext } from 'react';
import { AuthContext } from '@/context/AuthContext';

export default function StatsList() {
  const { user } = useContext(AuthContext);

  const stats = [
    {
      label: 'Encuestas Creadas',
      value: user?.surveys || 0,
      icon: BarChart3,
      color: 'text-blue-600',
      bg: 'bg-blue-50',
    },
    {
      label: 'Total Respuestas',
      value: user?.answers || 0,
      icon: Users,
      color: 'text-green-600',
      bg: 'bg-green-50',
    },
    {
      label: 'Total Vistas',
      value: user?.visits || 0,
      icon: Eye,
      color: 'text-purple-600',
      bg: 'bg-purple-50',
    },
  ];

  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
      {stats.map((stat, index) => (
        <Stat
          Icon={stat.icon}
          bg={stat.bg}
          color={stat.color}
          label={stat.label}
          value={stat.value}
          key={index}
        />
      ))}
    </div>
  );
}
