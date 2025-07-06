'use server';

import { redirect } from 'next/navigation';
import { createSession, deleteSession } from '../lib/session';

export async function loginUser(
  _prevState: unknown,
  formData: FormData
): Promise<{
  error?: string;
}> {
  const email = formData.get('email')?.toString();
  const password = formData.get('password')?.toString();

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
      error: Array.isArray(data.error)
        ? `The ${data.error[0].field} ${data.error[0].defaultMessage}`
        : data.error || 'Error desconocido al iniciar sesión.',
    };
  }

  await createSession(data.token);
  redirect('/dashboard');
}

export async function register(
  _prevState: unknown,
  formData: FormData
): Promise<{ error?: string }> {
  const username = formData.get('username')?.toString();
  const email = formData.get('email')?.toString();
  const password = formData.get('password')?.toString();

  const response = await fetch('http://localhost:8080/api/auth/signup', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ username, email, password }),
  });

  const data = await response.json();

  if (!response.ok) {
    return {
      error: Array.isArray(data.error)
        ? `The ${data.error[0].field} ${data.error[0].defaultMessage}`
        : data.error || 'Error desconocido al registrarse.',
    };
  }

  await createSession(data.token);
  redirect('/dashboard');
}

export async function logout() {
  await deleteSession();
  return redirect('/');
}
