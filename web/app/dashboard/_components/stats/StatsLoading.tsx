import { Loader } from 'lucide-react';
import Stat from './Stat';

export default function StatsLoading() {
  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <Stat
        Icon={Loader}
        color="text-purple-600"
        title="Loading stats..."
        value={''}
      />
    </div>
  );
}
