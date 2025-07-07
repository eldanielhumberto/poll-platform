'use client';

import { BarChart3, Eye } from 'lucide-react';
import { ServerResponse } from '@/interfaces/ServerResponse';
import { Survey } from '@/interfaces/Survey';
import { use } from 'react';
import Stat from './Stat';

interface Props {
  surveysData: Promise<ServerResponse<Survey[]>>;
}

export default function StatsList({ surveysData }: Props) {
  const { data } = use(surveysData);

  const surveysVisits = data.reduce((acc, survey) => acc + survey.visits, 0);
  const surveysCount = data.length;

  const stats = [
    {
      title: 'Total de Encuestas',
      value: surveysCount,
      icon: BarChart3,
      color: 'text-blue-600',
    },
    {
      title: 'Vistas Totales',
      value: surveysVisits,
      icon: Eye,
      color: 'text-purple-600',
    },
  ];

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      {stats.map((stat, index) => (
        <Stat
          title={stat.title}
          value={stat.value}
          Icon={stat.icon}
          color={stat.color}
          key={index}
        />
      ))}
    </div>
  );
}
