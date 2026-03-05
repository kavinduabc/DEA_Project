'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Badge } from '@/components/ui/badge'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Textarea } from '@/components/ui/textarea'
import { Separator } from '@/components/ui/separator'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog'
import {
  GraduationCap,
  ArrowLeft,
  Megaphone,
  BookOpen,
  MessageSquare,
  FileText,
  ClipboardList,
  Plus,
  Trash2,
  Download,
  Upload,
  Play,
  Link as LinkIcon,
  Clock,
  CheckCircle2,
  Power,
} from 'lucide-react'

// ── Types matching each service's response ────────────────────────────────────

// Classroom Service
interface Classroom {
  id: number
  teacherId: string
  title: string
  subject: string
  bannerImage: string | null
  inviteCode: string | null
  isActive: boolean
}

// Announcement Service
interface Announcement {
  id: number
  classroomId: number
  title: string
  content: string
  authorId: string
  createdAt: string
}

// Resources Service
interface Module {
  id: number
  classroomId: number
  title: string
  orderIndex: number
}
interface Resource {
  id: number
  moduleId: number
  title: string
  type: 'pdf' | 'video' | 'link'
  url: string
}

// Q&A Service
interface Question {
  id: number
  classroomId: number
  authorId: string
  authorName: string
  content: string
  createdAt: string
  replyCount: number
}

// Quizzes Service
interface Quiz {
  id: number
  classroomId: number
  title: string
  questionCount: number
  attempted: boolean
  score: number | null
}

// Assignment Service
interface Assignment {
  id: number
  classroomId: number
  title: string
  description: string
  dueDate: string
  submissionStatus: 'not_submitted' | 'submitted' | 'graded'
  grade: number | null
  feedback: string | null
}

export default function StudentClassView({ params }: { params: { id: string } }) {
  // ── Classroom Service ──────────────────────────────────────────────────────
  // TODO: GET /api/classrooms/{id}
  const classroom: Classroom = {
    id: Number(params.id),
    teacherId: 'teacher-uuid-1',
    title: 'Advanced Java Programming',
    subject: 'Programming',
    bannerImage: null,
    inviteCode: null,
    isActive: true,
  }

  // ── Announcement Service ───────────────────────────────────────────────────
  // TODO: GET /api/announcements/classroom/{classroomId}
  const announcements: Announcement[] = [
    { id: 1, classroomId: classroom.id, title: 'Welcome!', content: 'Welcome to the course. Please review the syllabus.', authorId: classroom.teacherId, createdAt: '2 days ago' },
    { id: 2, classroomId: classroom.id, title: 'Assignment Due Friday', content: 'Reminder: your first assignment is due this Friday at midnight.', authorId: classroom.teacherId, createdAt: '1 day ago' },
  ]

  // ── Resources Service ──────────────────────────────────────────────────────
  // TODO: GET /api/modules/classroom/{classroomId}
  const modules: Module[] = [
    { id: 1, classroomId: classroom.id, title: 'Getting Started', orderIndex: 1 },
    { id: 2, classroomId: classroom.id, title: 'Core Concepts', orderIndex: 2 },
  ]
  // TODO: GET /api/resources/module/{moduleId}
  const resources: Resource[] = [
    { id: 1, moduleId: 1, title: 'Introduction Slides', type: 'pdf', url: '#' },
    { id: 2, moduleId: 1, title: 'Setup Tutorial', type: 'link', url: '#' },
    { id: 3, moduleId: 2, title: 'Lecture Recording', type: 'video', url: '#' },
    { id: 4, moduleId: 2, title: 'Practice Exercises', type: 'pdf', url: '#' },
  ]

  // ── Q&A Service ────────────────────────────────────────────────────────────
  // TODO: GET /api/questions/classroom/{classroomId}
  const [questions, setQuestions] = useState<Question[]>([
    { id: 1, classroomId: classroom.id, authorId: 'student-1', authorName: 'Alice', content: 'What is polymorphism in Java?', createdAt: '3 hours ago', replyCount: 2 },
    { id: 2, classroomId: classroom.id, authorId: 'student-2', authorName: 'Bob', content: 'Can someone explain abstract classes vs interfaces?', createdAt: '1 hour ago', replyCount: 0 },
  ])
  const [showQuestionModal, setShowQuestionModal] = useState(false)
  const [questionForm, setQuestionForm] = useState({ content: '' })

  // ── Quizzes Service ────────────────────────────────────────────────────────
  // TODO: GET /api/quizzes/classroom/{classroomId}
  const quizzes: Quiz[] = [
    { id: 1, classroomId: classroom.id, title: 'OOP Basics Quiz', questionCount: 10, attempted: true, score: 85 },
    { id: 2, classroomId: classroom.id, title: 'Collections Quiz', questionCount: 8, attempted: false, score: null },
  ]

  // ── Assignment Service ─────────────────────────────────────────────────────
  // TODO: GET /api/assignments/classroom/{classroomId}
  const assignments: Assignment[] = [
    { id: 1, classroomId: classroom.id, title: 'OOP Design Exercise', description: 'Design a class hierarchy for a banking system.', dueDate: '2024-02-15', submissionStatus: 'graded', grade: 88, feedback: 'Good use of inheritance. Consider adding interfaces.' },
    { id: 2, classroomId: classroom.id, title: 'Collections Task', description: 'Implement a sorted contact list using Java Collections.', dueDate: '2024-02-22', submissionStatus: 'not_submitted', grade: null, feedback: null },
  ]

  // ── Q&A handlers ──────────────────────────────────────────────────────────
  // TODO: POST /api/questions → { classroomId, content }
  const handlePostQuestion = () => {
    console.log('POST /api/questions', { classroomId: classroom.id, content: questionForm.content })
    const newQ: Question = {
      id: Date.now(),
      classroomId: classroom.id,
      authorId: 'current-student-id',
      authorName: 'You',
      content: questionForm.content,
      createdAt: 'Just now',
      replyCount: 0,
    }
    setQuestions((prev) => [newQ, ...prev])
    setShowQuestionModal(false)
    setQuestionForm({ content: '' })
  }

  // TODO: DELETE /api/questions/{id} — only for own questions
  const handleDeleteQuestion = (id: number) => {
    console.log('DELETE /api/questions/' + id)
    setQuestions((prev) => prev.filter((q) => q.id !== id))
  }

  const resourceIcon = (type: Resource['type']) => {
    if (type === 'pdf') return <FileText className="h-4 w-4 text-red-500" />
    if (type === 'video') return <Play className="h-4 w-4 text-blue-500" />
    return <LinkIcon className="h-4 w-4 text-green-500" />
  }

  const submissionBadge = (status: Assignment['submissionStatus']) => {
    if (status === 'graded') return <Badge className="bg-green-100 text-green-800 border-green-200">Graded</Badge>
    if (status === 'submitted') return <Badge variant="secondary">Submitted</Badge>
    return <Badge variant="outline">Not Submitted</Badge>
  }

  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <header className="border-b bg-background/95 backdrop-blur sticky top-0 z-50">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <Link href="/student-dashboard" className="flex items-center gap-2 text-muted-foreground hover:text-foreground transition-colors">
              <ArrowLeft className="h-5 w-5" />
              Back to Dashboard
            </Link>
            <div className="flex items-center gap-2">
              <GraduationCap className="h-6 w-6 text-primary" />
              <span className="text-xl font-bold">OpenTutor</span>
            </div>
            <div className="w-24" />
          </div>
        </div>
      </header>

      {/* Class Banner — Classroom Service */}
      <div
        className="bg-gradient-to-r from-primary via-orange-500 to-orange-600 text-white py-10"
        style={classroom.bannerImage ? { backgroundImage: `linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5)),url(${classroom.bannerImage})`, backgroundSize: 'cover', backgroundPosition: 'center' } : {}}
      >
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex items-center gap-3 mb-3">
            <Badge className="bg-white/20 text-white border-white/30">{classroom.subject}</Badge>
            <Badge className={classroom.isActive ? 'bg-green-500/80 text-white border-green-400/30' : 'bg-gray-500/80 text-white border-gray-400/30'}>
              {classroom.isActive ? 'Active' : 'Inactive'}
            </Badge>
          </div>
          <h1 className="text-3xl sm:text-4xl font-bold">{classroom.title}</h1>
        </div>
      </div>

      {/* Tabs */}
      <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-6">
        <Tabs defaultValue="announcements" className="space-y-6">
          <TabsList className="grid w-full grid-cols-3 lg:grid-cols-5">
            <TabsTrigger value="announcements" className="gap-1 text-xs"><Megaphone className="h-4 w-4" /><span className="hidden sm:inline">Announcements</span></TabsTrigger>
            <TabsTrigger value="resources" className="gap-1 text-xs"><BookOpen className="h-4 w-4" /><span className="hidden sm:inline">Resources</span></TabsTrigger>
            <TabsTrigger value="qa" className="gap-1 text-xs"><MessageSquare className="h-4 w-4" /><span className="hidden sm:inline">Q&amp;A</span></TabsTrigger>
            <TabsTrigger value="quizzes" className="gap-1 text-xs"><ClipboardList className="h-4 w-4" /><span className="hidden sm:inline">Quizzes</span></TabsTrigger>
            <TabsTrigger value="assignments" className="gap-1 text-xs"><FileText className="h-4 w-4" /><span className="hidden sm:inline">Assignments</span></TabsTrigger>
          </TabsList>

          {/* ── ANNOUNCEMENTS — Announcement Service ── */}
          <TabsContent value="announcements" className="space-y-4">
            <h2 className="text-2xl font-bold">Announcements</h2>
            {/* GET /api/announcements/classroom/{classroomId} */}
            {announcements.length === 0 && <p className="text-muted-foreground text-center py-8">No announcements yet.</p>}
            {announcements.map((a) => (
              <Card key={a.id}>
                <CardHeader>
                  <CardTitle className="text-lg">{a.title}</CardTitle>
                  <CardDescription>{a.createdAt}</CardDescription>
                </CardHeader>
                <CardContent>
                  <p className="text-muted-foreground">{a.content}</p>
                </CardContent>
              </Card>
            ))}
          </TabsContent>

          {/* ── RESOURCES — Resources Service ── */}
          <TabsContent value="resources" className="space-y-4">
            <h2 className="text-2xl font-bold">Course Resources</h2>
            {/* GET /api/modules/classroom/{classroomId} */}
            {modules.map((mod) => (
              <Card key={mod.id}>
                <CardHeader>
                  <div className="flex items-center gap-2">
                    <Badge variant="outline">Module {mod.orderIndex}</Badge>
                    <CardTitle className="text-lg">{mod.title}</CardTitle>
                  </div>
                </CardHeader>
                <CardContent>
                  {/* GET /api/resources/module/{moduleId} */}
                  {resources.filter((r) => r.moduleId === mod.id).length === 0 ? (
                    <p className="text-sm text-muted-foreground">No resources available yet.</p>
                  ) : (
                    <div className="space-y-2">
                      {resources.filter((r) => r.moduleId === mod.id).map((res) => (
                        <div key={res.id} className="flex items-center justify-between p-3 rounded-lg border hover:border-primary transition-colors">
                          <div className="flex items-center gap-3">
                            {resourceIcon(res.type)}
                            <div>
                              <div className="font-medium text-sm">{res.title}</div>
                              <Badge variant="secondary" className="text-xs mt-0.5">{res.type.toUpperCase()}</Badge>
                            </div>
                          </div>
                          {/* View/Download Resource */}
                          <Button variant="outline" size="sm" className="gap-2" onClick={() => console.log('Access resource', res.id)}>
                            {res.type === 'pdf' ? <Download className="h-4 w-4" /> : <Play className="h-4 w-4" />}
                            {res.type === 'pdf' ? 'Download' : 'Open'}
                          </Button>
                        </div>
                      ))}
                    </div>
                  )}
                </CardContent>
              </Card>
            ))}
          </TabsContent>

          {/* ── Q&A — Q&A Service ── */}
          <TabsContent value="qa" className="space-y-4">
            <div className="flex items-center justify-between">
              <h2 className="text-2xl font-bold">Q&A Forum</h2>
              <Dialog open={showQuestionModal} onOpenChange={setShowQuestionModal}>
                <DialogTrigger asChild>
                  <Button className="gap-2"><Plus className="h-4 w-4" />Ask a Question</Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-lg">
                  <DialogHeader>
                    <DialogTitle>Post a Question</DialogTitle>
                    {/* Q&A Service: POST /api/questions */}
                    <DialogDescription>Ask something and your teacher or classmates will reply.</DialogDescription>
                  </DialogHeader>
                  <div className="space-y-4 py-4">
                    <div className="space-y-2">
                      <Label>Your Question</Label>
                      <Textarea placeholder="Type your question here..." value={questionForm.content} onChange={(e) => setQuestionForm({ content: e.target.value })} rows={4} />
                    </div>
                    <div className="flex gap-3">
                      <Button variant="outline" className="flex-1" onClick={() => setShowQuestionModal(false)}>Cancel</Button>
                      <Button className="flex-1" onClick={handlePostQuestion} disabled={!questionForm.content.trim()}>Post Question</Button>
                    </div>
                  </div>
                </DialogContent>
              </Dialog>
            </div>
            {/* GET /api/questions/classroom/{classroomId} */}
            {questions.length === 0 && <p className="text-muted-foreground text-center py-8">No questions yet. Be the first to ask!</p>}
            {questions.map((q) => (
              <Card key={q.id}>
                <CardHeader>
                  <div className="flex items-start justify-between">
                    <div>
                      <CardTitle className="text-base">{q.content}</CardTitle>
                      <CardDescription>by {q.authorName} · {q.createdAt}</CardDescription>
                    </div>
                    <div className="flex items-center gap-2">
                      <Badge variant="secondary">{q.replyCount} replies</Badge>
                      {/* Delete own question — DELETE /api/questions/{id} */}
                      {q.authorName === 'You' && (
                        <Button variant="ghost" size="icon" onClick={() => handleDeleteQuestion(q.id)}>
                          <Trash2 className="h-4 w-4 text-destructive" />
                        </Button>
                      )}
                    </div>
                  </div>
                </CardHeader>
                <CardContent>
                  {/* View Replies — GET /api/replies/question/{questionId} */}
                  {/* Reply — POST /api/replies → { questionId, content } */}
                  <Button variant="outline" size="sm" className="gap-2" onClick={() => console.log('View/Reply to question', q.id)}>
                    <MessageSquare className="h-4 w-4" />View & Reply ({q.replyCount})
                  </Button>
                </CardContent>
              </Card>
            ))}
          </TabsContent>

          {/* ── QUIZZES — Quizzes Service ── */}
          <TabsContent value="quizzes" className="space-y-4">
            <h2 className="text-2xl font-bold">Quizzes</h2>
            {/* GET /api/quizzes/classroom/{classroomId} */}
            {quizzes.length === 0 && <p className="text-muted-foreground text-center py-8">No quizzes yet.</p>}
            <div className="grid md:grid-cols-2 gap-4">
              {quizzes.map((quiz) => (
                <Card key={quiz.id}>
                  <CardHeader>
                    <div className="flex items-start justify-between">
                      <CardTitle className="text-lg">{quiz.title}</CardTitle>
                      {quiz.attempted ? (
                        <Badge className="bg-green-100 text-green-800 border-green-200">
                          <CheckCircle2 className="h-3 w-3 mr-1" />Done
                        </Badge>
                      ) : (
                        <Badge variant="outline">
                          <Clock className="h-3 w-3 mr-1" />Pending
                        </Badge>
                      )}
                    </div>
                    <CardDescription>{quiz.questionCount} questions</CardDescription>
                  </CardHeader>
                  <CardContent className="space-y-3">
                    {quiz.attempted && quiz.score !== null && (
                      <div className="flex items-center gap-2 text-sm">
                        <span className="text-muted-foreground">Your Score:</span>
                        <span className="font-bold text-primary text-lg">{quiz.score}%</span>
                      </div>
                    )}
                    {quiz.attempted ? (
                      // View attempt history — GET /api/attempts/quiz/{quizId}?studentId=...
                      <Button variant="outline" size="sm" className="w-full" onClick={() => console.log('View attempts for quiz', quiz.id)}>
                        View Attempt History
                      </Button>
                    ) : (
                      // Start quiz — POST /api/attempts → { quizId, studentId }
                      <Button size="sm" className="w-full gap-2" onClick={() => console.log('Start quiz', quiz.id)}>
                        <Play className="h-4 w-4" />Start Quiz
                      </Button>
                    )}
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>


          {/* ── ASSIGNMENTS — Assignment Service ── */}
          <TabsContent value="assignments" className="space-y-4">
            <h2 className="text-2xl font-bold">Assignments</h2>
            {/* GET /api/assignments/classroom/{classroomId} */}
            {assignments.length === 0 && <p className="text-muted-foreground text-center py-8">No assignments yet.</p>}
            <div className="space-y-4">
              {assignments.map((a) => (
                <Card key={a.id}>
                  <CardHeader>
                    <div className="flex items-start justify-between">
                      <div>
                        <CardTitle className="text-lg">{a.title}</CardTitle>
                        <CardDescription className="flex items-center gap-2 mt-1">
                          <Clock className="h-3 w-3" />Due: {a.dueDate}
                        </CardDescription>
                      </div>
                      {submissionBadge(a.submissionStatus)}
                    </div>
                  </CardHeader>
                  <CardContent className="space-y-4">
                    <p className="text-sm text-muted-foreground">{a.description}</p>

                    {/* Grade & Feedback — from Assignment Service */}
                    {a.submissionStatus === 'graded' && (
                      <div className="bg-muted/50 rounded-lg p-3 space-y-2">
                        <div className="flex items-center gap-2">
                          <span className="text-sm text-muted-foreground">Grade:</span>
                          <span className="font-bold text-primary">{a.grade}%</span>
                        </div>
                        {a.feedback && (
                          <div>
                            <span className="text-sm text-muted-foreground">Feedback: </span>
                            <span className="text-sm">{a.feedback}</span>
                          </div>
                        )}
                      </div>
                    )}

                    <div className="flex gap-2">
                      {a.submissionStatus === 'not_submitted' && (
                        // Submit Assignment — POST /api/submissions → { assignmentId, studentId }
                        // Upload File — part of submission payload
                        <Button size="sm" className="gap-2" onClick={() => console.log('Submit assignment', a.id)}>
                          <Upload className="h-4 w-4" />Submit Assignment
                        </Button>
                      )}
                      {a.submissionStatus === 'submitted' && (
                        // View submitted file — GET /api/submissions/assignment/{id}?studentId=...
                        <Button variant="outline" size="sm" className="gap-2" onClick={() => console.log('View submission', a.id)}>
                          <FileText className="h-4 w-4" />View My Submission
                        </Button>
                      )}
                      {a.submissionStatus === 'graded' && (
                        <Button variant="outline" size="sm" className="gap-2" onClick={() => console.log('View submission', a.id)}>
                          <FileText className="h-4 w-4" />View Submission
                        </Button>
                      )}
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>
        </Tabs>
      </div>
    </div>
  )
}

