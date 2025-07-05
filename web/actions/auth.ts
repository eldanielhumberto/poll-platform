'use server';

import { redirect } from 'next/navigation';
import { createSession, deleteSession } from '../lib/session';

export async function loginUser(email: string, password: string) {
  const response = await fetch('http://localhost:8080/api/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ email, password }),
  });

  const data = await response.json();

  if (!response.ok) {
    throw new Error(
      Array.isArray(data.error)
        ? `The ${data.error[0].field} ${data.error[0].defaultMessage}`
        : data.error || 'Error desconocido al iniciar sesión.'
    );
  }

  await createSession(data.token);
}

export async function register(
  username: string,
  email: string,
  password: string
) {
  const response = await fetch('http://localhost:8080/api/auth/signup', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ username, email, password }),
  });

  const data = await response.json();

  if (!response.ok) {
    throw new Error(
      Array.isArray(data.error)
        ? `The ${data.error[0].field} ${data.error[0].defaultMessage}`
        : data.error || 'Error desconocido al registrarse.'
    );
  }

  await createSession(data.token);
}

export async function logout() {
  await deleteSession();
  return redirect('/');
}
