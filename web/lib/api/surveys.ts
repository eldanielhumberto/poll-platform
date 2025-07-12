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

  return data;
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

  return data;
}

export async function getSurvey(
  surveyId: string
): Promise<ServerResponse<Survey>> {
  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/surveys/get?id=${surveyId}`
  );

  const data = await response.json();

  if (!response.ok) {
    console.log(data);
    return {
      message: 'Get survey',
      error: 'Failed to fetch survey',
      data: {} as Survey,
    };
  }

  return data;
}

export async function deleteSurvey(
  surveyId: string
): Promise<ServerResponse<null>> {
  const session = await getSession();
  if (!session) return { message: '', data: null, error: 'No session found' };

  const response = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/surveys/${surveyId}`,
    {
      method: 'DELETE',
      headers: {
        Authorization: session,
      },
    }
  );

  const data = await response.json();

  if (!response.ok) {
    console.log(data);
    return {
      message: 'Delete survey',
      error: 'Failed to delete survey',
      data: null,
    };
  }

  return data;
}
