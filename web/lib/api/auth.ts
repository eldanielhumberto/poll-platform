import { User } from '@/interfaces/User';
import { getSession } from '../session';

export async function getUser(): Promise<User | null> {
  const session = await getSession();
  if (!session) return null;

  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/auth/profile`,
    {
      headers: {
        Authorization: session,
      },
    }
  );

  // TODO: Manejar mejor los errores, puedo retornar un objeto con error como { error: '...' }
  if (!response.ok) throw new Error('Failed to fetch user profile');

  const data = await response.json();
  return data;
}
