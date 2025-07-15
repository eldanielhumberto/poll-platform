'use client';

import { Dispatch, SetStateAction } from 'react';
import useSWR, { Fetcher } from 'swr';

import { ServerResponse } from '@/interfaces/ServerResponse';
import { Category } from '@/interfaces/Category';
import { Survey } from '@/interfaces/Survey';

import { Textarea } from '@/components/ui/textarea';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';

interface Props {
  survey: Survey;
  setSurvey: Dispatch<SetStateAction<Survey>>;
}

const fetcher: Fetcher<ServerResponse<Category[]>> = (url: string) =>
  fetch(url).then((r) => r.json());

export default function BasicInformationForm({ survey, setSurvey }: Props) {
  const { data, isLoading } = useSWR(
    `${process.env.NEXT_PUBLIC_API_URL}/categories`,
    fetcher
  );

  const getCategoryDetails = (
    categoryName: string
  ): { id: string; name: string; color: string } => {
    return data?.data.find((c) => c.name === categoryName) as Category;
  };

  return (
    <Card>
      <CardHeader>
        <CardTitle>Información Básica</CardTitle>
        <CardDescription>
          Define el título y descripción de tu encuesta
        </CardDescription>
      </CardHeader>
      <CardContent className="space-y-4">
        <div className="space-y-2">
          <Label htmlFor="title">Título de la encuesta *</Label>
          <Input
            id="title"
            placeholder="Ej: Satisfacción del cliente 2024"
            value={survey.title}
            onChange={(e) =>
              setSurvey((prev) => ({ ...prev, title: e.target.value }))
            }
          />
        </div>
        <div className="space-y-2">
          <Label htmlFor="description">Descripción</Label>
          <Textarea
            id="description"
            placeholder="Describe el propósito de tu encuesta..."
            value={survey.description}
            onChange={(e) =>
              setSurvey((prev) => ({
                ...prev,
                description: e.target.value,
              }))
            }
            rows={3}
          />
        </div>
        {!isLoading ? (
          <div className="space-y-2">
            <Label htmlFor="category">Categoría</Label>
            <Select
              value={survey.category.name}
              onValueChange={(value) =>
                setSurvey((prev) => ({
                  ...prev,
                  category: getCategoryDetails(value),
                }))
              }
            >
              <SelectTrigger>
                <SelectValue placeholder="Selecciona una categoría" />
              </SelectTrigger>
              <SelectContent>
                {data &&
                  data.data.map((category) => (
                    <SelectItem key={category.id} value={category.name}>
                      {category.name}
                    </SelectItem>
                  ))}
              </SelectContent>
            </Select>
          </div>
        ) : (
          <div>Loading...</div>
        )}
      </CardContent>
    </Card>
  );
}
