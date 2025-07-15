'use client';

import { Survey as ISurvey } from '@/interfaces/Survey';

import Survey from './Survey';

interface Props {
  surveys: ISurvey[];
}

export default function SurveysList({ surveys }: Props) {
  return (
    <div className="space-y-4">
      {surveys.map((survey) => (
        <Survey
          key={survey.id}
          title={survey.title}
          description={survey.description}
          visits={survey.visits}
          createdAt={survey.createdAt}
          author={survey.author}
          answers={survey.answers}
          category={survey.category}
          questions={survey.questions}
          id={survey.id}
        />
      ))}
    </div>
  );
}
