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
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'
import {
  GraduationCap,
  ArrowLeft,
  Copy,
  Check,
  Settings,
  Power,
  PowerOff,
  RefreshCw,
  Megaphone,
  BookOpen,
  MessageSquare,
  FileText,
  ClipboardList,
  Plus,
  Edit,
  Trash2,
  Upload,
  Users,
  Share2,
} from 'lucide-react'

// ── Shared Types ──────────────────────────────────────────────────────────────


// Classroom Service — ClassroomResponseDTO
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
  createdAt: string
}

// Assignment Service
interface Assignment {
  id: number
  classroomId: number
  title: string
  description: string
  dueDate: string
  submissionCount: number
}

// Enrollment Service
interface Enrollment {
  id: number
  studentId: string
  studentName: string
  studentEmail: string
  enrolledAt: string
}

export default function TeacherClassView({ params }: { params: { id: string } }) {
  // ── Classroom Service state ────────────────────────────────────────────────
  // TODO: GET /api/classrooms/{id}
  const [classroom, setClassroom] = useState<Classroom>({
    id: Number(params.id),
    teacherId: 'teacher-uuid-1',
    title: 'Advanced Java Programming',
    subject: 'Programming',
    bannerImage: null,
    inviteCode: 'JVA729',
    isActive: true,
  })
  const [copiedCode, setCopiedCode] = useState(false)
  const [editForm, setEditForm] = useState({
    title: classroom.title,
    subject: classroom.subject,
    bannerImage: classroom.bannerImage ?? '',
    inviteCode: classroom.inviteCode ?? '',
  })

  // ── Announcement Service state ─────────────────────────────────────────────
  // TODO: GET /api/announcements/classroom/{classroomId}
  const [announcements, setAnnouncements] = useState<Announcement[]>([
    { id: 1, classroomId: Number(params.id), title: 'Welcome!', content: 'Welcome to the course.', authorId: 'teacher-uuid-1', createdAt: '2 days ago' },
    { id: 2, classroomId: Number(params.id), title: 'Assignment Due Friday', content: 'Reminder: assignment is due this Friday.', authorId: 'teacher-uuid-1', createdAt: '1 day ago' },
  ])
  const [showAnnouncementModal, setShowAnnouncementModal] = useState(false)
  const [editingAnnouncement, setEditingAnnouncement] = useState<Announcement | null>(null)
  const [announcementForm, setAnnouncementForm] = useState({ title: '', content: '' })

  // ── Resources Service state ────────────────────────────────────────────────
  // TODO: GET /api/modules/classroom/{classroomId}
  const [modules, setModules] = useState<Module[]>([
    { id: 1, classroomId: Number(params.id), title: 'Getting Started', orderIndex: 1 },
    { id: 2, classroomId: Number(params.id), title: 'Core Concepts', orderIndex: 2 },
  ])
  // TODO: GET /api/resources/module/{moduleId}
  const [resources] = useState<Resource[]>([
    { id: 1, moduleId: 1, title: 'Intro Slides', type: 'pdf', url: '#' },
    { id: 2, moduleId: 1, title: 'Setup Guide', type: 'link', url: '#' },
    { id: 3, moduleId: 2, title: 'Lecture Video', type: 'video', url: '#' },
  ])
  const [showModuleModal, setShowModuleModal] = useState(false)
  const [moduleForm, setModuleForm] = useState({ title: '' })

  // ── Q&A Service state ──────────────────────────────────────────────────────
  // TODO: GET /api/questions/classroom/{classroomId}
  const [questions] = useState<Question[]>([
    { id: 1, classroomId: Number(params.id), authorId: 'student-1', authorName: 'Alice', content: 'What is polymorphism?', createdAt: '3 hours ago', replyCount: 2 },
    { id: 2, classroomId: Number(params.id), authorId: 'student-2', authorName: 'Bob', content: 'How do interfaces differ from abstract classes?', createdAt: '1 hour ago', replyCount: 0 },
  ])

  // ── Quizzes Service state ──────────────────────────────────────────────────
  // TODO: GET /api/quizzes/classroom/{classroomId}
  const [quizzes] = useState<Quiz[]>([
    { id: 1, classroomId: Number(params.id), title: 'OOP Basics Quiz', questionCount: 10, createdAt: '1 week ago' },
    { id: 2, classroomId: Number(params.id), title: 'Collections Quiz', questionCount: 8, createdAt: '2 days ago' },
  ])
  const [showQuizModal, setShowQuizModal] = useState(false)
  const [quizForm, setQuizForm] = useState({ title: '' })

  // ── Assignment Service state ───────────────────────────────────────────────
  // TODO: GET /api/assignments/classroom/{classroomId}
  const [assignments] = useState<Assignment[]>([
    { id: 1, classroomId: Number(params.id), title: 'OOP Design Exercise', description: 'Design a class hierarchy.', dueDate: '2024-02-15', submissionCount: 12 },
    { id: 2, classroomId: Number(params.id), title: 'Collections Task', description: 'Implement a sorted list.', dueDate: '2024-02-22', submissionCount: 5 },
  ])
  const [showAssignmentModal, setShowAssignmentModal] = useState(false)
  const [assignmentForm, setAssignmentForm] = useState({ title: '', description: '', dueDate: '' })

  // ── Enrollment Service state ───────────────────────────────────────────────
  // TODO: GET /api/enrollments/classroom/{classroomId}
  const [enrollments] = useState<Enrollment[]>([
    { id: 1, studentId: 'student-1', studentName: 'Alice Johnson', studentEmail: 'alice@example.com', enrolledAt: '2 weeks ago' },
    { id: 2, studentId: 'student-2', studentName: 'Bob Smith', studentEmail: 'bob@example.com', enrolledAt: '1 week ago' },
    { id: 3, studentId: 'student-3', studentName: 'Carol Lee', studentEmail: 'carol@example.com', enrolledAt: '3 days ago' },
  ])

  // ── Classroom Service handlers ─────────────────────────────────────────────
  const copyInviteCode = () => {
    if (!classroom.inviteCode) return
    navigator.clipboard.writeText(classroom.inviteCode)
    setCopiedCode(true)
    setTimeout(() => setCopiedCode(false), 2000)
  }

  // TODO: PUT /api/classrooms/{id}
  const handleSaveChanges = () => {
    console.log('PUT /api/classrooms/' + classroom.id, { ...classroom, ...editForm })
    setClassroom((prev) => ({ ...prev, ...editForm, bannerImage: editForm.bannerImage || null, inviteCode: editForm.inviteCode || null }))
  }

  // TODO: PUT /api/classrooms/{id} — toggle isActive
  const handleToggleActive = () => {
    console.log('PUT /api/classrooms/' + classroom.id, { ...classroom, isActive: !classroom.isActive })
    setClassroom((prev) => ({ ...prev, isActive: !prev.isActive }))
  }

  // TODO: PUT /api/classrooms/{id} — generate new inviteCode
  const handleGenerateNewCode = () => {
    const newCode = Array.from({ length: 6 }, () => 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789'[Math.floor(Math.random() * 36)]).join('')
    console.log('PUT /api/classrooms/' + classroom.id, { ...classroom, inviteCode: newCode })
    setClassroom((prev) => ({ ...prev, inviteCode: newCode }))
    setEditForm((prev) => ({ ...prev, inviteCode: newCode }))
  }

  // ── Announcement Service handlers ──────────────────────────────────────────
  // TODO: POST /api/announcements → { classroomId, title, content }
  const handleCreateAnnouncement = () => {
    console.log('POST /api/announcements', { classroomId: classroom.id, ...announcementForm })
    const newAnnouncement: Announcement = { id: Date.now(), classroomId: classroom.id, ...announcementForm, authorId: classroom.teacherId, createdAt: 'Just now' }
    setAnnouncements((prev) => [newAnnouncement, ...prev])
    setShowAnnouncementModal(false)
    setAnnouncementForm({ title: '', content: '' })
  }

  // TODO: PUT /api/announcements/{id}
  const handleEditAnnouncement = (a: Announcement) => {
    setEditingAnnouncement(a)
    setAnnouncementForm({ title: a.title, content: a.content })
    setShowAnnouncementModal(true)
  }

  const handleUpdateAnnouncement = () => {
    if (!editingAnnouncement) return
    console.log('PUT /api/announcements/' + editingAnnouncement.id, announcementForm)
    setAnnouncements((prev) => prev.map((a) => a.id === editingAnnouncement.id ? { ...a, ...announcementForm } : a))
    setShowAnnouncementModal(false)
    setEditingAnnouncement(null)
    setAnnouncementForm({ title: '', content: '' })
  }

  // TODO: DELETE /api/announcements/{id}
  const handleDeleteAnnouncement = (id: number) => {
    console.log('DELETE /api/announcements/' + id)
    setAnnouncements((prev) => prev.filter((a) => a.id !== id))
  }

  // ── Resources Service handlers ─────────────────────────────────────────────
  // TODO: POST /api/modules → { classroomId, title, orderIndex }
  const handleCreateModule = () => {
    console.log('POST /api/modules', { classroomId: classroom.id, title: moduleForm.title, orderIndex: modules.length + 1 })
    const newModule: Module = { id: Date.now(), classroomId: classroom.id, title: moduleForm.title, orderIndex: modules.length + 1 }
    setModules((prev) => [...prev, newModule])
    setShowModuleModal(false)
    setModuleForm({ title: '' })
  }

  // TODO: DELETE /api/modules/{id}
  const handleDeleteModule = (id: number) => {
    console.log('DELETE /api/modules/' + id)
    setModules((prev) => prev.filter((m) => m.id !== id))
  }

  // ── Quiz Service handlers ──────────────────────────────────────────────────
  // TODO: POST /api/quizzes → { classroomId, title }
  const handleCreateQuiz = () => {
    console.log('POST /api/quizzes', { classroomId: classroom.id, title: quizForm.title })
    setShowQuizModal(false)
    setQuizForm({ title: '' })
  }

  // TODO: DELETE /api/quizzes/{id}
  const handleDeleteQuiz = (id: number) => console.log('DELETE /api/quizzes/' + id)

  // ── Assignment Service handlers ────────────────────────────────────────────
  // TODO: POST /api/assignments → { classroomId, title, description, dueDate }
  const handleCreateAssignment = () => {
    console.log('POST /api/assignments', { classroomId: classroom.id, ...assignmentForm })
    setShowAssignmentModal(false)
    setAssignmentForm({ title: '', description: '', dueDate: '' })
  }

  // TODO: DELETE /api/assignments/{id}
  const handleDeleteAssignment = (id: number) => console.log('DELETE /api/assignments/' + id)

  // ── Enrollment Service handlers ────────────────────────────────────────────
  // TODO: DELETE /api/enrollments/{enrollmentId}
  const handleRemoveStudent = (enrollmentId: number) => {
    console.log('DELETE /api/enrollments/' + enrollmentId)
  }

  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <header className="border-b bg-background/95 backdrop-blur sticky top-0 z-50">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <Link href="/teacher-dashboard" className="flex items-center gap-2 text-muted-foreground hover:text-foreground transition-colors">
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
        className="bg-gradient-to-r from-primary via-orange-500 to-orange-600 text-white py-8"
        style={classroom.bannerImage ? { backgroundImage: `linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5)),url(${classroom.bannerImage})`, backgroundSize: 'cover', backgroundPosition: 'center' } : {}}
      >
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex flex-col lg:flex-row lg:items-start lg:justify-between gap-4">
            <div className="flex-1">
              <div className="flex items-center gap-2 mb-2">
                <Badge className="bg-white/20 text-white border-white/30">{classroom.subject}</Badge>
                <Badge className={classroom.isActive ? 'bg-green-500/80 text-white border-green-400/30' : 'bg-gray-500/80 text-white border-gray-400/30'}>
                  {classroom.isActive ? 'Active' : 'Inactive'}
                </Badge>
              </div>
              <h1 className="text-3xl sm:text-4xl font-bold mb-1">{classroom.title}</h1>
              <p className="opacity-80 text-sm">{enrollments.length} enrolled students</p>
            </div>
            {classroom.inviteCode && (
              <Card className="bg-white/10 backdrop-blur border-white/20 text-white">
                <CardContent className="p-4 text-center">
                  <p className="text-sm opacity-90 mb-2">Invite Code</p>
                  <div className="flex items-center gap-2">
                    <code className="text-3xl font-bold tracking-wider">{classroom.inviteCode}</code>
                    <Button variant="ghost" size="icon" className="h-8 w-8 text-white hover:bg-white/20" onClick={copyInviteCode}>
                      {copiedCode ? <Check className="h-5 w-5" /> : <Copy className="h-5 w-5" />}
                    </Button>
                  </div>
                </CardContent>
              </Card>
            )}
          </div>
        </div>
      </div>

      {/* Tabs */}
      <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-6">
        <Tabs defaultValue="announcements" className="space-y-6">
          <TabsList className="grid w-full grid-cols-3 lg:grid-cols-7">
            <TabsTrigger value="announcements" className="gap-1 text-xs"><Megaphone className="h-4 w-4" /><span className="hidden sm:inline">Announcements</span></TabsTrigger>
            <TabsTrigger value="people" className="gap-1 text-xs"><Users className="h-4 w-4" /><span className="hidden sm:inline">People</span></TabsTrigger>
            <TabsTrigger value="resources" className="gap-1 text-xs"><BookOpen className="h-4 w-4" /><span className="hidden sm:inline">Resources</span></TabsTrigger>
            <TabsTrigger value="qa" className="gap-1 text-xs"><MessageSquare className="h-4 w-4" /><span className="hidden sm:inline">Q&amp;A</span></TabsTrigger>
            <TabsTrigger value="quizzes" className="gap-1 text-xs"><ClipboardList className="h-4 w-4" /><span className="hidden sm:inline">Quizzes</span></TabsTrigger>
            <TabsTrigger value="assignments" className="gap-1 text-xs"><FileText className="h-4 w-4" /><span className="hidden sm:inline">Assignments</span></TabsTrigger>
            <TabsTrigger value="settings" className="gap-1 text-xs"><Settings className="h-4 w-4" /><span className="hidden sm:inline">Settings</span></TabsTrigger>
          </TabsList>

          {/* ── ANNOUNCEMENTS TAB — Announcement Service ── */}
          <TabsContent value="announcements" className="space-y-4">
            <div className="flex items-center justify-between">
              <h2 className="text-2xl font-bold">Announcements</h2>
              <Dialog open={showAnnouncementModal} onOpenChange={(open) => { setShowAnnouncementModal(open); if (!open) { setEditingAnnouncement(null); setAnnouncementForm({ title: '', content: '' }) } }}>
                <DialogTrigger asChild>
                  <Button className="gap-2"><Plus className="h-4 w-4" />New Announcement</Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-lg">
                  <DialogHeader>
                    <DialogTitle>{editingAnnouncement ? 'Edit Announcement' : 'Create Announcement'}</DialogTitle>
                    <DialogDescription>
                      {/* Announcement Service: POST /api/announcements or PUT /api/announcements/{id} */}
                      Share important updates with your students.
                    </DialogDescription>
                  </DialogHeader>
                  <div className="space-y-4 py-4">
                    <div className="space-y-2">
                      <Label htmlFor="ann-title">Title</Label>
                      <Input id="ann-title" placeholder="Announcement title" value={announcementForm.title} onChange={(e) => setAnnouncementForm({ ...announcementForm, title: e.target.value })} />
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="ann-content">Content</Label>
                      <Textarea id="ann-content" placeholder="Write your announcement..." value={announcementForm.content} onChange={(e) => setAnnouncementForm({ ...announcementForm, content: e.target.value })} rows={4} />
                    </div>
                    <div className="flex gap-3 pt-2">
                      <Button variant="outline" className="flex-1" onClick={() => setShowAnnouncementModal(false)}>Cancel</Button>
                      <Button className="flex-1" onClick={editingAnnouncement ? handleUpdateAnnouncement : handleCreateAnnouncement} disabled={!announcementForm.title || !announcementForm.content}>
                        {editingAnnouncement ? 'Save Changes' : 'Post'}
                      </Button>
                    </div>
                  </div>
                </DialogContent>
              </Dialog>
            </div>
            {announcements.length === 0 && <p className="text-muted-foreground text-center py-8">No announcements yet.</p>}
            {announcements.map((a) => (
              <Card key={a.id}>
                <CardHeader>
                  <div className="flex items-start justify-between">
                    <div>
                      <CardTitle className="text-lg">{a.title}</CardTitle>
                      <CardDescription>{a.createdAt}</CardDescription>
                    </div>
                    <div className="flex gap-1">
                      {/* Share Announcement — Announcement Service */}
                      <Button variant="ghost" size="icon" title="Share" onClick={() => console.log('Share announcement', a.id)}>
                        <Share2 className="h-4 w-4" />
                      </Button>
                      {/* Edit — PUT /api/announcements/{id} */}
                      <Button variant="ghost" size="icon" onClick={() => handleEditAnnouncement(a)}>
                        <Edit className="h-4 w-4" />
                      </Button>
                      {/* Delete — DELETE /api/announcements/{id} */}
                      <Button variant="ghost" size="icon" onClick={() => handleDeleteAnnouncement(a.id)}>
                        <Trash2 className="h-4 w-4 text-destructive" />
                      </Button>
                    </div>
                  </div>
                </CardHeader>
                <CardContent>
                  <p className="text-muted-foreground">{a.content}</p>
                </CardContent>
              </Card>
            ))}
          </TabsContent>

          {/* ── PEOPLE TAB — Enrollment Service ── */}
          <TabsContent value="people" className="space-y-4">
            <div className="flex items-center justify-between">
              <h2 className="text-2xl font-bold">Enrolled Students ({enrollments.length})</h2>
            </div>
            <Card>
              <CardContent className="p-0">
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead>Student</TableHead>
                      <TableHead>Enrolled</TableHead>
                      <TableHead className="text-right">Actions</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {enrollments.map((e) => (
                      <TableRow key={e.id}>
                        <TableCell>
                          <div>
                            <div className="font-medium">{e.studentName}</div>
                            <div className="text-sm text-muted-foreground">{e.studentEmail}</div>
                          </div>
                        </TableCell>
                        <TableCell className="text-muted-foreground">{e.enrolledAt}</TableCell>
                        <TableCell className="text-right">
                          {/* Remove Student — DELETE /api/enrollments/{id} */}
                          <Button variant="ghost" size="sm" className="text-destructive hover:text-destructive" onClick={() => handleRemoveStudent(e.id)}>
                            Remove
                          </Button>
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </CardContent>
            </Card>
          </TabsContent>

          {/* ── RESOURCES TAB — Resources Service ── */}
          <TabsContent value="resources" className="space-y-4">
            <div className="flex items-center justify-between">
              <h2 className="text-2xl font-bold">Modules & Resources</h2>
              <Dialog open={showModuleModal} onOpenChange={setShowModuleModal}>
                <DialogTrigger asChild>
                  <Button className="gap-2"><Plus className="h-4 w-4" />Add Module</Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-md">
                  <DialogHeader>
                    <DialogTitle>Create Module</DialogTitle>
                    {/* Resources Service: POST /api/modules */}
                    <DialogDescription>Add a new module to this classroom.</DialogDescription>
                  </DialogHeader>
                  <div className="space-y-4 py-4">
                    <div className="space-y-2">
                      <Label>Module Title</Label>
                      <Input placeholder="e.g., Introduction to OOP" value={moduleForm.title} onChange={(e) => setModuleForm({ title: e.target.value })} />
                    </div>
                    <div className="flex gap-3">
                      <Button variant="outline" className="flex-1" onClick={() => setShowModuleModal(false)}>Cancel</Button>
                      <Button className="flex-1" onClick={handleCreateModule} disabled={!moduleForm.title}>Create</Button>
                    </div>
                  </div>
                </DialogContent>
              </Dialog>
            </div>
            <div className="space-y-4">
              {modules.map((mod) => (
                <Card key={mod.id}>
                  <CardHeader>
                    <div className="flex items-center justify-between">
                      <div className="flex items-center gap-2">
                        <Badge variant="outline">Module {mod.orderIndex}</Badge>
                        <CardTitle className="text-lg">{mod.title}</CardTitle>
                      </div>
                      <div className="flex gap-1">
                        {/* Upload Resource — Resources Service: POST /api/resources */}
                        <Button variant="outline" size="sm" className="gap-2" onClick={() => console.log('Upload resource to module', mod.id)}>
                          <Upload className="h-4 w-4" />Upload
                        </Button>
                        {/* Delete Module — DELETE /api/modules/{id} */}
                        <Button variant="ghost" size="icon" onClick={() => handleDeleteModule(mod.id)}>
                          <Trash2 className="h-4 w-4 text-destructive" />
                        </Button>
                      </div>
                    </div>
                  </CardHeader>
                  <CardContent>
                    {/* Resources — GET /api/resources/module/{moduleId} */}
                    {resources.filter((r) => r.moduleId === mod.id).length === 0 ? (
                      <p className="text-sm text-muted-foreground">No resources yet. Upload files or links.</p>
                    ) : (
                      <div className="space-y-2">
                        {resources.filter((r) => r.moduleId === mod.id).map((res) => (
                          <div key={res.id} className="flex items-center justify-between p-2 rounded-lg bg-muted/50 text-sm">
                            <div className="flex items-center gap-2">
                              <FileText className="h-4 w-4 text-primary" />
                              <span>{res.title}</span>
                              <Badge variant="secondary" className="text-xs">{res.type.toUpperCase()}</Badge>
                            </div>
                            {/* Delete Resource — DELETE /api/resources/{id} */}
                            <Button variant="ghost" size="icon" className="h-6 w-6" onClick={() => console.log('DELETE /api/resources/' + res.id)}>
                              <Trash2 className="h-3 w-3 text-destructive" />
                            </Button>
                          </div>
                        ))}
                      </div>
                    )}
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>

          {/* ── Q&A TAB — Q&A Service ── */}
          <TabsContent value="qa" className="space-y-4">
            <div className="flex items-center justify-between">
              <h2 className="text-2xl font-bold">Q&A Forum</h2>
            </div>
            <p className="text-sm text-muted-foreground">
              {/* Q&A Service: GET /api/questions/classroom/{classroomId} */}
              Students can post questions here. As a teacher you can reply and delete posts.
            </p>
            {questions.length === 0 && <p className="text-muted-foreground text-center py-8">No questions yet.</p>}
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
                      {/* Delete Question — DELETE /api/questions/{id} */}
                      <Button variant="ghost" size="icon" onClick={() => console.log('DELETE /api/questions/' + q.id)}>
                        <Trash2 className="h-4 w-4 text-destructive" />
                      </Button>
                    </div>
                  </div>
                </CardHeader>
                <CardContent>
                  {/* View Replies — GET /api/replies/question/{questionId} */}
                  <Button variant="outline" size="sm" className="gap-2">
                    <MessageSquare className="h-4 w-4" />View & Reply
                  </Button>
                </CardContent>
              </Card>
            ))}
          </TabsContent>

          {/* ── QUIZZES TAB — Quizzes Service ── */}
          <TabsContent value="quizzes" className="space-y-4">
            <div className="flex items-center justify-between">
              <h2 className="text-2xl font-bold">Quizzes</h2>
              <Dialog open={showQuizModal} onOpenChange={setShowQuizModal}>
                <DialogTrigger asChild>
                  <Button className="gap-2"><Plus className="h-4 w-4" />Create Quiz</Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-md">
                  <DialogHeader>
                    <DialogTitle>Create Quiz</DialogTitle>
                    {/* Quizzes Service: POST /api/quizzes */}
                    <DialogDescription>Create a new quiz for this classroom.</DialogDescription>
                  </DialogHeader>
                  <div className="space-y-4 py-4">
                    <div className="space-y-2">
                      <Label>Quiz Title</Label>
                      <Input placeholder="e.g., Week 1 Quiz" value={quizForm.title} onChange={(e) => setQuizForm({ title: e.target.value })} />
                    </div>
                    <div className="flex gap-3">
                      <Button variant="outline" className="flex-1" onClick={() => setShowQuizModal(false)}>Cancel</Button>
                      <Button className="flex-1" onClick={handleCreateQuiz} disabled={!quizForm.title}>Create</Button>
                    </div>
                  </div>
                </DialogContent>
              </Dialog>
            </div>
            {quizzes.length === 0 && <p className="text-muted-foreground text-center py-8">No quizzes yet.</p>}
            <div className="grid md:grid-cols-2 gap-4">
              {quizzes.map((quiz) => (
                <Card key={quiz.id}>
                  <CardHeader>
                    <div className="flex items-start justify-between">
                      <div>
                        <CardTitle className="text-lg">{quiz.title}</CardTitle>
                        <CardDescription>{quiz.questionCount} questions · Created {quiz.createdAt}</CardDescription>
                      </div>
                      <div className="flex gap-1">
                        {/* Add Questions — POST /api/questions (quiz question) */}
                        <Button variant="outline" size="sm" className="gap-1" onClick={() => console.log('Add questions to quiz', quiz.id)}>
                          <Plus className="h-3 w-3" />Questions
                        </Button>
                        {/* Delete Quiz — DELETE /api/quizzes/{id} */}
                        <Button variant="ghost" size="icon" onClick={() => handleDeleteQuiz(quiz.id)}>
                          <Trash2 className="h-4 w-4 text-destructive" />
                        </Button>
                      </div>
                    </div>
                  </CardHeader>
                  <CardContent>
                    {/* View Attempts — GET /api/attempts/quiz/{quizId} */}
                    <Button variant="outline" size="sm" className="gap-2" onClick={() => console.log('View attempts for quiz', quiz.id)}>
                      <ClipboardList className="h-4 w-4" />View Attempts
                    </Button>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>

          {/* ── ASSIGNMENTS TAB — Assignment Service ── */}
          <TabsContent value="assignments" className="space-y-4">
            <div className="flex items-center justify-between">
              <h2 className="text-2xl font-bold">Assignments</h2>
              <Dialog open={showAssignmentModal} onOpenChange={setShowAssignmentModal}>
                <DialogTrigger asChild>
                  <Button className="gap-2"><Plus className="h-4 w-4" />Create Assignment</Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-lg">
                  <DialogHeader>
                    <DialogTitle>Create Assignment</DialogTitle>
                    {/* Assignment Service: POST /api/assignments */}
                    <DialogDescription>Create a new assignment for this classroom.</DialogDescription>
                  </DialogHeader>
                  <div className="space-y-4 py-4">
                    <div className="space-y-2">
                      <Label>Title</Label>
                      <Input placeholder="Assignment title" value={assignmentForm.title} onChange={(e) => setAssignmentForm({ ...assignmentForm, title: e.target.value })} />
                    </div>
                    <div className="space-y-2">
                      <Label>Description</Label>
                      <Textarea placeholder="What should students do?" value={assignmentForm.description} onChange={(e) => setAssignmentForm({ ...assignmentForm, description: e.target.value })} rows={3} />
                    </div>
                    <div className="space-y-2">
                      <Label>Due Date</Label>
                      <Input type="date" value={assignmentForm.dueDate} onChange={(e) => setAssignmentForm({ ...assignmentForm, dueDate: e.target.value })} />
                    </div>
                    <div className="flex gap-3">
                      <Button variant="outline" className="flex-1" onClick={() => setShowAssignmentModal(false)}>Cancel</Button>
                      <Button className="flex-1" onClick={handleCreateAssignment} disabled={!assignmentForm.title}>Create</Button>
                    </div>
                  </div>
                </DialogContent>
              </Dialog>
            </div>
            {assignments.length === 0 && <p className="text-muted-foreground text-center py-8">No assignments yet.</p>}
            <div className="grid md:grid-cols-2 gap-4">
              {assignments.map((a) => (
                <Card key={a.id}>
                  <CardHeader>
                    <div className="flex items-start justify-between">
                      <div>
                        <CardTitle className="text-lg">{a.title}</CardTitle>
                        <CardDescription>Due: {a.dueDate} · {a.submissionCount} submissions</CardDescription>
                      </div>
                      <div className="flex gap-1">
                        {/* Edit — PUT /api/assignments/{id} */}
                        <Button variant="ghost" size="icon" onClick={() => console.log('Edit assignment', a.id)}>
                          <Edit className="h-4 w-4" />
                        </Button>
                        {/* Delete — DELETE /api/assignments/{id} */}
                        <Button variant="ghost" size="icon" onClick={() => handleDeleteAssignment(a.id)}>
                          <Trash2 className="h-4 w-4 text-destructive" />
                        </Button>
                      </div>
                    </div>
                  </CardHeader>
                  <CardContent>
                    <p className="text-sm text-muted-foreground mb-3">{a.description}</p>
                    {/* View Submissions + Grade — GET /api/submissions/assignment/{assignmentId} */}
                    <Button variant="outline" size="sm" className="gap-2" onClick={() => console.log('View submissions for assignment', a.id)}>
                      <FileText className="h-4 w-4" />View & Grade Submissions
                    </Button>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>

          {/* ── SETTINGS TAB — Classroom Service ── */}
          <TabsContent value="settings" className="space-y-6">
            <h2 className="text-2xl font-bold">Classroom Settings</h2>
            <div className="grid lg:grid-cols-2 gap-6">

              {/* General Info — PUT /api/classrooms/{id} */}
              <Card>
                <CardHeader>
                  <CardTitle>General Information</CardTitle>
                  <CardDescription>Update title, subject, and banner image</CardDescription>
                </CardHeader>
                <CardContent className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="classTitle">Class Title <span className="text-destructive">*</span></Label>
                    <Input id="classTitle" value={editForm.title} onChange={(e) => setEditForm({ ...editForm, title: e.target.value })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="subject">Subject <span className="text-destructive">*</span></Label>
                    <Input id="subject" value={editForm.subject} onChange={(e) => setEditForm({ ...editForm, subject: e.target.value })} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="bannerImage">Banner Image URL</Label>
                    <Input id="bannerImage" placeholder="https://example.com/banner.jpg" value={editForm.bannerImage} onChange={(e) => setEditForm({ ...editForm, bannerImage: e.target.value })} />
                  </div>
                  <Button className="w-full" onClick={handleSaveChanges} disabled={!editForm.title || !editForm.subject}>Save Changes</Button>
                </CardContent>
              </Card>

              {/* Invite Code — PUT /api/classrooms/{id} */}
              <Card>
                <CardHeader>
                  <CardTitle>Invite Code</CardTitle>
                  <CardDescription>Share this with students to join the classroom</CardDescription>
                </CardHeader>
                <CardContent className="space-y-4">
                  <div className="space-y-2">
                    <Label>Current Code</Label>
                    <div className="flex items-center gap-2">
                      <Input value={classroom.inviteCode ?? 'No code set'} readOnly className="font-mono text-xl text-center uppercase tracking-widest" />
                      {classroom.inviteCode && (
                        <Button variant="outline" onClick={copyInviteCode}>
                          {copiedCode ? <Check className="h-4 w-4" /> : <Copy className="h-4 w-4" />}
                        </Button>
                      )}
                    </div>
                  </div>
                  <Separator />
                  <Button variant="outline" className="w-full gap-2" onClick={handleGenerateNewCode}>
                    <RefreshCw className="h-4 w-4" />Generate New Code
                  </Button>
                </CardContent>
              </Card>

              {/* Activate / Deactivate — PUT /api/classrooms/{id} */}
              <Card>
                <CardHeader>
                  <CardTitle>Class Status</CardTitle>
                  <CardDescription>Control whether students can access this classroom</CardDescription>
                </CardHeader>
                <CardContent>
                  <div className="flex items-center justify-between p-4 border rounded-lg">
                    <div>
                      <div className="font-medium">
                        Status:{' '}
                        <span className={classroom.isActive ? 'text-green-600' : 'text-muted-foreground'}>
                          {classroom.isActive ? 'Active' : 'Inactive'}
                        </span>
                      </div>
                      <div className="text-sm text-muted-foreground mt-1">
                        {classroom.isActive ? 'Students can access this classroom.' : 'This classroom is hidden from students.'}
                      </div>
                    </div>
                    <Button variant={classroom.isActive ? 'destructive' : 'default'} className="gap-2 ml-4" onClick={handleToggleActive}>
                      {classroom.isActive ? <><PowerOff className="h-4 w-4" />Deactivate</> : <><Power className="h-4 w-4" />Activate</>}
                    </Button>
                  </div>
                </CardContent>
              </Card>

              {/* View Details — GET /api/classrooms/{id} */}
              <Card>
                <CardHeader>
                  <CardTitle>Classroom Details</CardTitle>
                  <CardDescription>Read-only summary from Classroom Service</CardDescription>
                </CardHeader>
                <CardContent className="space-y-3 text-sm">
                  {[
                    { label: 'Classroom ID', value: classroom.id },
                    { label: 'Teacher ID', value: classroom.teacherId },
                    { label: 'Title', value: classroom.title },
                    { label: 'Subject', value: classroom.subject },
                    { label: 'Invite Code', value: classroom.inviteCode ?? '—' },
                  ].map(({ label, value }, i) => (
                    <div key={i}>
                      <div className="flex justify-between">
                        <span className="text-muted-foreground">{label}</span>
                        <span className="font-mono font-medium truncate max-w-[180px]">{value}</span>
                      </div>
                      {i < 4 && <Separator className="mt-3" />}
                    </div>
                  ))}
                </CardContent>
              </Card>

            </div>
          </TabsContent>
        </Tabs>
      </div>
    </div>
  )
}
