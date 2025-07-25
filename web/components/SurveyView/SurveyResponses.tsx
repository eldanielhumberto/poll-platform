import { Response } from '@/interfaces/Response';
import { Survey } from '@/interfaces/Survey';
import { BarChart3, CheckCircle } from 'lucide-react';
import { Card, CardContent, CardHeader, CardTitle } from '../ui/card';
import { User } from '@/interfaces/User';

interface Props {
  survey: Survey;
  respondent: User;
}

export default function SurveyResponses({ survey, respondent }: Props) {
  const getUserAnswer = (questionId: string): string => {
    const answer = (survey.answers as Response[]).find(
      (r) => r.question.id === questionId && r.respondent.id === respondent.id
    );
    if (!answer) return '';

    return answer.option.optionText;
  };

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between mb-6">
        <h2 className="text-xl font-semibold text-gray-900 flex items-center">
          <BarChart3 className="h-5 w-5 mr-2 text-blue-600" />
          Tus respuestas
        </h2>
      </div>

      {survey.questions.map((question, index) => (
        <Card key={question.id} className="border-0 shadow-sm">
          <CardHeader>
            <CardTitle className="text-lg flex items-center">
              <span className="bg-blue-100 text-blue-600 rounded-full w-6 h-6 flex items-center justify-center text-sm font-bold mr-3">
                {index + 1}
              </span>
              {question.questionText}
            </CardTitle>
            <CardContent className="space-y-6">
              <div className="bg-blue-50 border border-blue-200 rounded-lg p-4">
                <h4 className="font-medium text-blue-800 mb-2 flex items-center">
                  <CheckCircle className="h-4 w-4 mr-2" />
                  Tu respuesta:
                </h4>
                <p className="text-blue-700">{getUserAnswer(question.id)}</p>
              </div>
            </CardContent>
          </CardHeader>
        </Card>
      ))}
    </div>
  );
}
