import { ServerResponse } from '@/interfaces/ServerResponse';
import { Survey as ISurvey } from '@/interfaces/Survey';
import { use } from 'react';
import Survey from './Survey';

interface Props {
  surveys: Promise<ServerResponse<ISurvey[]>>;
}

export default function SurveysList({ surveys }: Props) {
  const { data } = use(surveys);

  return (
    <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
      {data.map((survey) => (
        <Survey
          id={survey.id}
          author={survey.author}
          createdAt={survey.createdAt}
          description={survey.description}
          title={survey.title}
          visits={survey.visits}
          key={survey.id}
        />
      ))}
    </div>
  );
}
