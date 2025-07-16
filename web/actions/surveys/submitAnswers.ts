'use server';

import { submitAnswers } from '@/lib/api/surveys';

export default async function submitAnswersAction(options: string[]) {
  await submitAnswers(options);
}
