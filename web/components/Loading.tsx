import Navbar from './Navbar';

interface Props {
  message?: string;
}

export default function Loading({ message = 'Loading...' }: Props) {
  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <main className="container mx-auto px-4 py-8">
        <div className="flex items-center justify-center h-screen">
          <p className="text-gray-600">{message}</p>
        </div>
      </main>
    </div>
  );
}
