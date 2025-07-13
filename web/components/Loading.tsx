interface Props {
  message?: string;
}

export default function Loading({ message = 'Loading...' }: Props) {
  return (
    <main className="container mx-auto px-4 py-8 flex items-center justify-center flex-1">
      <div className="flex items-center justify-center">
        <p className="text-gray-600">{message}</p>
      </div>
    </main>
  );
}
