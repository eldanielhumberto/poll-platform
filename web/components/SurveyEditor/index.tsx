import { v4 as uuidv4 } from 'uuid';
import { Send } from 'lucide-react';
import { useState } from 'react';

import { Tabs, TabsContent, TabsList, TabsTrigger } from '../ui/tabs';
import { Separator } from '../ui/separator';
import { Button } from '../ui/button';

import QuestionsSectionEditor from './QuestionsSectionEditor';
import BasicInformationForm from './BasicInformationEditor';
import { SurveyView } from '../SurveyView';

import { Question } from '@/interfaces/Question';
import { Survey } from '@/interfaces/Survey';

interface Props {
  initialSurvey: Survey;
  publishSurvey: (survey: Survey) => void;
}

export default function SurveyEditor({ initialSurvey, publishSurvey }: Props) {
  const [activeTab, setActiveTab] = useState('editor');
  const [survey, setSurvey] = useState<Survey>(initialSurvey);

  const addQuestion = () => {
    const newQuestion: Question = {
      id: `q_${Date.now()}`,
      questionText: '',
      options: [
        { id: uuidv4(), optionText: 'Opcion 1' },
        { id: uuidv4(), optionText: 'Opcion 2' },
      ],
    };
    setSurvey((prev) => ({
      ...prev,
      questions: [...prev.questions, newQuestion],
    }));
  };

  const updateQuestion = (questionId: string, updates: Partial<Question>) => {
    setSurvey((prev) => ({
      ...prev,
      questions: prev.questions.map((q) =>
        q.id === questionId ? { ...q, ...updates } : q
      ),
    }));
  };

  const deleteQuestion = (questionId: string) => {
    setSurvey((prev) => ({
      ...prev,
      questions: prev.questions.filter((q) => q.id !== questionId),
    }));
  };

  const moveQuestion = (questionId: string, direction: 'up' | 'down') => {
    setSurvey((prev) => {
      const index = prev.questions.findIndex((q) => q.id === questionId);

      const newQuestions = [...prev.questions];
      const [removed] = newQuestions.splice(index, 1);

      const newIndex = direction === 'up' ? index - 1 : index + 1;
      newQuestions.splice(newIndex, 0, removed);

      return { ...prev, questions: newQuestions };
    });
  };

  const canPublish = survey.title.trim() !== '' && survey.questions.length > 0;

  return (
    <>
      {/* Header */}
      <div className="flex items-center justify-between mb-8">
        <div className="flex items-center space-x-4">
          <Separator orientation="vertical" className="h-6" />
          <div>
            <h1 className="text-2xl font-bold text-gray-900">
              Crear Nueva Encuesta
            </h1>
            <p className="text-gray-600">Diseña tu encuesta paso a paso</p>
          </div>
        </div>
        <div className="flex items-center space-x-3">
          <Button onClick={() => publishSurvey(survey)} disabled={!canPublish}>
            <Send className="h-4 w-4 mr-2" />
            Publicar
          </Button>
        </div>
      </div>

      <Tabs
        value={activeTab}
        onValueChange={setActiveTab}
        className="space-y-6"
      >
        <TabsList className="grid w-full grid-cols-2">
          <TabsTrigger value="editor">Editor</TabsTrigger>
          <TabsTrigger value="preview">Vista Previa</TabsTrigger>
        </TabsList>

        {/* Editor Tab */}
        <TabsContent value="editor" className="space-y-6">
          <BasicInformationForm setSurvey={setSurvey} survey={survey} />

          <QuestionsSectionEditor
            survey={survey}
            addQuestion={addQuestion}
            deleteQuestion={deleteQuestion}
            updateQuestion={updateQuestion}
            moveQuestion={moveQuestion}
          />
        </TabsContent>

        {/* Preview Tab */}
        <TabsContent value="preview">
          <SurveyView survey={survey} isEditor />
        </TabsContent>
      </Tabs>
    </>
  );
}
