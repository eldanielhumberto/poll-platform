import { Category } from '@/interfaces/Category';

export async function getCategories(): Promise<Category[]> {
  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/categories`,
    {
      cache: 'force-cache',
    }
  );

  if (!response.ok) {
    console.error('Failed to fetch categories');
    return [];
  }

  const { data } = await response.json();
  data.push({
    id: 'all',
    name: 'Todas',
    surveysCount: 0,
  } as Category);
  console.log(data);

  return data;
}
