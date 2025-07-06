'use server';

import { redirect } from 'next/navigation';
import { createSession, deleteSession } from '../lib/session';
import { signin, signup } from '@/lib/api/auth';
import { ERROR_MESSAGES } from '@/constants/messages';

export async function loginUser(
  _prevState: unknown,
  formData: FormData
): Promise<{
  error?: string;
}> {
  const email = formData.get('email')?.toString();
  const password = formData.get('password')?.toString();

  if (!email || !password) return { error: ERROR_MESSAGES.REQUIRED_FIELDS };

  try {
    const { data, error } = await signin(email, password);
    if (error) return { error };

    await createSession(data);
  } catch (error) {
    console.log(error);
    return { error: ERROR_MESSAGES.GENERIC };
  }

  redirect('/dashboard');
}

export async function register(
  _prevState: unknown,
  formData: FormData
): Promise<{ error?: string }> {
  const username = formData.get('username')?.toString();
  const email = formData.get('email')?.toString();
  const password = formData.get('password')?.toString();

  if (!username || !email || !password)
    return { error: ERROR_MESSAGES.REQUIRED_FIELDS };

  try {
    const { data, error } = await signup(username, email, password);
    if (error) return { error };

    await createSession(data);
  } catch (error) {
    console.log(error);
    return { error: ERROR_MESSAGES.GENERIC };
  }

  redirect('/dashboard');
}

export async function logout() {
  await deleteSession();
  return redirect('/');
}
