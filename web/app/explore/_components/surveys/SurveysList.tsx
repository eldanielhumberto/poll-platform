import { Survey as ISurvey } from '@/interfaces/Survey';
import Survey from './Survey';

interface Props {
  surveys: ISurvey[];
}

export default function SurveysList({ surveys }: Props) {
  return (
    <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
      {surveys.map((survey) => (
        <Survey
          id={survey.id}
          author={survey.author}
          createdAt={survey.createdAt}
          description={survey.description}
          title={survey.title}
          visits={survey.visits}
          answers={survey.answers}
          category={survey.category}
          key={survey.id}
        />
      ))}
    </div>
  );
}
