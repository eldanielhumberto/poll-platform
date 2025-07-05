import { Survey } from '@/interfaces/Survey';
import { getSession } from '../session';

export async function getUserSurveys(): Promise<Survey[]> {
  const session = await getSession();
  if (!session) throw new Error('No session found');

  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/surveys/get/me`,
    {
      headers: {
        Authorization: session,
      },
    }
  );

  if (!response.ok) throw new Error('Failed to fetch surveys');

  const data = await response.json();
  return data.data;
}
