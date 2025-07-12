import { Question as IQuestion } from '@/interfaces/Question';
import Question from './Question';

interface Props {
  questions: IQuestion[];
  answers: Record<string, string>;
  currentQuestion: number;
  handleSingleChoice: (questionId: string, value: string) => void;
}

export default function QuestionsList({
  questions,
  answers,
  currentQuestion,
  handleSingleChoice,
}: Props) {
  return (
    <div className="space-y-8">
      {questions.map((question, index) => (
        <Question
          key={question.id}
          question={question}
          index={index}
          answers={answers}
          currentQuestion={currentQuestion}
          handleSingleChoice={handleSingleChoice}
        />
      ))}
    </div>
  );
}
