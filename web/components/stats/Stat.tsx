import { Card, CardContent } from '@/components/ui/card';

interface Props {
  label: string;
  value: string | number;
  color: string;
  bg: string;
  Icon: React.ComponentType<{ className?: string }>;
}

export default function Stat({ Icon, bg, color, label, value }: Props) {
  return (
    <Card className="border-0 shadow-sm hover:shadow-md transition-shadow">
      <CardContent className="p-6">
        <div className="flex items-center justify-between">
          <div>
            <p className="text-sm text-gray-600 mb-1">{label}</p>
            <p className="text-3xl font-bold text-gray-900">{value}</p>
          </div>
          <div className={`p-4 rounded-full ${bg}`}>
            <Icon className={`h-8 w-8 ${color}`} />
          </div>
        </div>
      </CardContent>
    </Card>
  );
}
