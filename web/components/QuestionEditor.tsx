'use client';

import { Card, CardContent, CardHeader } from '@/components/ui/card';
import { Question } from '@/interfaces/Question';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';

import {
  Trash2,
  GripVertical,
  ChevronUp,
  ChevronDown,
  Plus,
  X,
} from 'lucide-react';

interface QuestionEditorProps {
  question: Question;
  index: number;
  totalQuestions: number;
  onUpdate: (updates: Partial<Question>) => void;
  onDelete: () => void;
  onMove: (direction: 'up' | 'down') => void;
  hasResponses?: boolean;
}

export function QuestionEditor({
  question,
  index,
  totalQuestions,
  onUpdate,
  onDelete,
  onMove,
  hasResponses = false,
}: QuestionEditorProps) {
  const addOption = () => {
    if (!question.options) return;
    const newOptions = [
      ...question.options,
      {
        id: Date.now().toString(36),
        optionText: `Opción ${question.options.length + 1}`,
      },
    ];
    onUpdate({ options: newOptions });
  };

  const updateOption = (index: number, value: string) => {
    if (!question.options) return;
    const newOptions = [...question.options];
    newOptions[index] = {
      id: index.toString(),
      optionText: value,
    };
    onUpdate({ options: newOptions });
  };

  const removeOption = (index: number) => {
    if (!question.options || question.options.length <= 2) return;
    const newOptions = question.options.filter((_, i) => i !== index);
    onUpdate({ options: newOptions });
  };

  return (
    <Card className="border-l-4 border-l-blue-500">
      <CardHeader className="pb-3">
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-3">
            <div className="flex items-center space-x-2">
              <GripVertical className="h-4 w-4 text-gray-400 cursor-move" />
              <span className="text-sm font-medium text-gray-500">
                #{index + 1}
              </span>
            </div>
          </div>
          <div className="flex items-center space-x-2">
            <Button
              variant="ghost"
              size="sm"
              onClick={() => onMove('up')}
              disabled={index === 0}
            >
              <ChevronUp className="h-4 w-4" />
            </Button>
            <Button
              variant="ghost"
              size="sm"
              onClick={() => onMove('down')}
              disabled={index === totalQuestions - 1}
            >
              <ChevronDown className="h-4 w-4" />
            </Button>
            <Button
              variant="ghost"
              size="sm"
              onClick={onDelete}
              className="text-red-600 hover:text-red-700"
            >
              <Trash2 className="h-4 w-4" />
            </Button>
          </div>
        </div>
      </CardHeader>
      {hasResponses && (
        <div className="px-6 pb-3">
          <div className="text-xs text-orange-600 bg-orange-50 p-2 rounded border border-orange-200">
            ⚠️ Esta pregunta ya tiene respuestas. Los cambios pueden afectar el
            análisis de datos.
          </div>
        </div>
      )}

      <CardContent className="space-y-4">
        {/* Question Text */}
        <div className="space-y-2">
          <Label>Pregunta *</Label>
          <Input
            placeholder="Escribe tu pregunta aquí..."
            value={question.questionText}
            onChange={(e) => onUpdate({ questionText: e.target.value })}
          />
        </div>

        {/* Options for choice questions */}
        <div className="space-y-3">
          <div className="flex items-center justify-between">
            <Label>Opciones</Label>
            <Button variant="outline" size="sm" onClick={addOption}>
              <Plus className="h-4 w-4 mr-1" />
              Agregar Opción
            </Button>
          </div>
          <div className="space-y-2">
            {question.options?.map((option, optionIndex) => (
              <div key={optionIndex} className="flex items-center space-x-2">
                <Input
                  value={option.optionText}
                  onChange={(e) => updateOption(optionIndex, e.target.value)}
                  placeholder={`Opción ${optionIndex + 1}`}
                />
                {question.options && question.options.length > 2 && (
                  <Button
                    variant="ghost"
                    size="sm"
                    onClick={() => removeOption(optionIndex)}
                    className="text-red-600 hover:text-red-700"
                  >
                    <X className="h-4 w-4" />
                  </Button>
                )}
              </div>
            ))}
          </div>
        </div>
      </CardContent>
    </Card>
  );
}
