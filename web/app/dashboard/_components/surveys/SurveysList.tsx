'use client';

import { ServerResponse } from '@/interfaces/ServerResponse';
import { Survey as ISurvey } from '@/interfaces/Survey';
import { use } from 'react';

import Survey from './Survey';

interface Props {
  surveysData: Promise<ServerResponse<ISurvey[]>>;
}

export default function SurveysList({ surveysData }: Props) {
  const { data: surveys } = use(surveysData);

  return (
    <div className="space-y-4">
      {surveys.map((survey) => (
        <Survey
          key={survey.id}
          title={survey.title}
          description={survey.description}
          visits={survey.visits}
          createdAt={survey.createdAt}
          id={survey.id}
        />
      ))}
    </div>
  );
}
