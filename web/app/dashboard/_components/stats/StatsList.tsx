import { BarChart3, Eye } from 'lucide-react';
import Stat from './Stat';

interface Props {
  surveysCount: number;
  surveysVisits: number;
}

export default function StatsList({ surveysCount, surveysVisits }: Props) {
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
