import StatsList from '@/components/stats/StatsList';

import Configuration from './_components/Configuration';
import Header from './_components/Header';

export default function ProfilePage() {
  return (
    <main className="container mx-auto px-4 py-8">
      <Header />
      <StatsList />
      <Configuration />
    </main>
  );
}
