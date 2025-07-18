import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Label } from '@/components/ui/label';
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group';
import { Question as IQuestion } from '@/interfaces/Question';

interface Props {
  handleSingleChoice: (questionId: string, value: string) => void;
  answers: Record<string, string>;
  currentQuestion: number;
  question: IQuestion;
  index: number;
}

export default function Question({
  question,
  index,
  answers,
  currentQuestion,
  handleSingleChoice,
}: Props) {
  return (
    <Card
      key={question.id}
      className={`border-0 shadow-sm ${
        index !== currentQuestion ? 'hidden' : ''
      }`}
    >
      <CardHeader>
        <CardTitle className="text-lg flex items-center">
          {question.questionText}
        </CardTitle>
      </CardHeader>
      <CardContent>
        <RadioGroup
          value={answers[question.questionText] || ''}
          onValueChange={(value) =>
            handleSingleChoice(question.questionText, value)
          }
        >
          {question.options?.map((option, index) => (
            <div
              key={index}
              className="flex items-center space-x-2 rounded-lg hover:bg-gray-50 pl-3"
            >
              <RadioGroupItem
                value={option.id}
                id={`${index}-${option.id}`}
                className=""
              />
              <Label
                htmlFor={`${index}-${option.id}`}
                className="flex-1 cursor-pointer hover:bg-gray-50 pr-3 pb-3 pt-3"
              >
                {option.optionText}{' '}
                <b>
                  {index}-{option.id}
                </b>
              </Label>
            </div>
          ))}
        </RadioGroup>
      </CardContent>
    </Card>
  );
}
