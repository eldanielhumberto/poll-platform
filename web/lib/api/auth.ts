import { User } from '@/interfaces/User';
import { getSession } from '../session';
import { ServerResponse } from '@/interfaces/ServerResponse';

export async function signup(
  username: string,
  email: string,
  password: string
): Promise<ServerResponse<string>> {
  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/auth/signup`,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, email, password }),
    }
  );

  const data = await response.json();
  if (!response.ok) {
    return {
      message: data.message,
      data: '',
      error: Array.isArray(data.error)
        ? `The ${data.error[0].field} ${data.error[0].defaultMessage}`
        : data.error || 'Error desconocido al registrarse.',
    };
  }

  return { message: data.message, error: data.error, data: data.data };
}

export async function signin(
  email: string,
  password: string
): Promise<ServerResponse<string>> {
  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/auth/login`,
    {
      method: 'POST',
      body: JSON.stringify({ email, password }),
      headers: {
        'Content-Type': 'application/json',
      },
    }
  );

  const data = await response.json();
  if (!response.ok) {
    return {
      message: data.message,
      data: '',
      error: Array.isArray(data.error)
        ? `The ${data.error[0].field} ${data.error[0].defaultMessage}`
        : data.error || 'Error desconocido al iniciar sesión.',
    };
  }

  return { data: data.data, message: data.message };
}

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

  const data = await response.json();

  // Check errors
  if (!response.ok) {
    console.log(data, response.status);
    return null;
  }

  return data;
}
