interface Props {
  error: string;
}

export default function ErrorCard({ error }: Props) {
  return (
    <div className="mb-4">
      <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative text-sm shadow-sm animate-fade-in text-center">
        <span className="font-semibold">Error: </span>
        {error}
      </div>
    </div>
  );
}
