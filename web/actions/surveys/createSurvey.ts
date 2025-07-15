'use server';

import { SurveyDataToSend } from '@/interfaces/Survey';
import { createSurvey } from '@/lib/api/surveys';
import { redirect } from 'next/navigation';

export async function createSurveyAction(dataToSend: SurveyDataToSend) {
  await createSurvey(dataToSend);
  redirect('/dashboard');
}
