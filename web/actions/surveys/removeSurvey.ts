'use server';

import { deleteSurvey } from '@/lib/api/surveys';
import { revalidatePath } from 'next/cache';

export default async function removeSurvey(surveyId: string) {
  await deleteSurvey(surveyId);
  revalidatePath('/dashboard');
}
