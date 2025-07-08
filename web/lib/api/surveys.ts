import { Survey } from '@/interfaces/Survey';
import { getSession } from '../session';
import { ServerResponse } from '@/interfaces/ServerResponse';

export async function getUserSurveys(): Promise<ServerResponse<Survey[]>> {
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

  const data = await response.json();

  if (!response.ok) {
    console.log(data);
    return {
      message: 'Get surveys',
      error: 'Failed to fetch surveys',
      data: [],
    };
  }

  return { message: data.message, data: data.data, error: data.error };
}

export async function getSurveys(): Promise<ServerResponse<Survey[]>> {
  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/surveys/get`
  );

  const data = await response.json();

  if (!response.ok) {
    console.log(data);
    return {
      message: 'Get surveys',
      error: 'Failed to fetch surveys',
      data: [],
    };
  }

  return { message: data.message, data: data.data, error: data.error };
}
