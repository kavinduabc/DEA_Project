'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Button } from '@/components/ui/button'
import { Card, CardDescription, CardHeader, CardTitle, CardContent } from '@/components/ui/card'
import { Badge } from '@/components/ui/badge'
import {
  GraduationCap,
  LogIn,
  UserPlus,
  LayoutDashboard,
  BookOpen,
  ClipboardList,
  FileText,
  Megaphone,
  MessageSquare,
  KeyRound,
  UserCircle,
  Clock,
  ChevronDown,
} from 'lucide-react'


// ── Latest Announcements component ───────────────────────────────────────────
// Data comes from Announcement Service: GET /api/announcements/latest
interface Announcement {
  id: number
  classroomTitle: string
  subject: string
  title: string
  content: string
  postedAt: string
}

const MOCK_ANNOUNCEMENTS: Announcement[] = [
  {
    id: 1,
    classroomTitle: 'Advanced Java Programming',
    subject: 'Programming',
    title: 'Mid-term exam schedule released',
    content: 'The mid-term exam will be held on March 15th. Please review chapters 1–6 and ensure you have your IDE set up.',
    postedAt: '2 hours ago',
  },
  {
    id: 2,
    classroomTitle: 'Data Structures & Algorithms',
    subject: 'Computer Science',
    title: 'New module uploaded: Sorting Algorithms',
    content: 'A new module covering Bubble Sort, Merge Sort, and Quick Sort has been uploaded. Check the Resources tab.',
    postedAt: '5 hours ago',
  },
  {
    id: 3,
    classroomTitle: 'Web Development Bootcamp',
    subject: 'Web Development',
    title: 'Assignment 2 deadline extended',
    content: 'The deadline for Assignment 2 has been extended to Friday at midnight. Make sure to submit via the Assignments tab.',
    postedAt: '1 day ago',
  },
  {
    id: 4,
    classroomTitle: 'UI/UX Design Fundamentals',
    subject: 'Design',
    title: 'Guest lecture this Thursday',
    content: 'We have a special guest lecturer joining us this Thursday to talk about design systems and component libraries.',
    postedAt: '1 day ago',
  },
  {
    id: 5,
    classroomTitle: 'Python for Beginners',
    subject: 'Programming',
    title: 'Welcome to the course!',
    content: 'Welcome everyone! Please introduce yourself in the Q&A forum and review the getting-started resources.',
    postedAt: '2 days ago',
  },
  {
    id: 6,
    classroomTitle: 'Data Structures & Algorithms',
    subject: 'Computer Science',
    title: 'Quiz 1 results published',
    content: 'Quiz 1 results are now available. Check your score in the Quizzes tab. Overall the class performed well!',
    postedAt: '3 days ago',
  },
]

function LatestAnnouncements() {
  const [showAll, setShowAll] = useState(false)
  const visible = showAll ? MOCK_ANNOUNCEMENTS : MOCK_ANNOUNCEMENTS.slice(0, 3)

  return (
    <section className="py-20 bg-muted/30">
      <div className="container mx-auto px-4 sm:px-6 lg:px-8">
        <div className="max-w-6xl mx-auto">

          <div className="text-center mb-14">
            <div className="inline-flex items-center gap-2 bg-primary/10 text-primary px-4 py-2 rounded-full text-sm font-medium mb-4">
              <Megaphone className="h-4 w-4" />
              Latest Announcements
            </div>
            <h2 className="text-3xl sm:text-4xl font-bold text-foreground mb-4">
              What&apos;s happening in classrooms
            </h2>
            <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
              Recent announcements posted by teachers across all active classrooms.
            </p>
          </div>

          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
            {visible.map((a) => (
              <Card key={a.id} className="hover:shadow-md transition-shadow flex flex-col">
                <CardHeader className="pb-3">
                  <div className="flex items-center gap-2 mb-2">
                    <Badge variant="outline" className="text-xs">{a.subject}</Badge>
                  </div>
                  <p className="text-xs text-muted-foreground font-medium truncate">{a.classroomTitle}</p>
                  <CardTitle className="text-base leading-snug">{a.title}</CardTitle>
                </CardHeader>
                <CardContent className="flex-1 flex flex-col justify-between gap-4">
                  <CardDescription className="text-sm line-clamp-3">
                    {a.content}
                  </CardDescription>
                  <div className="flex items-center gap-1.5 text-xs text-muted-foreground">
                    <Clock className="h-3.5 w-3.5" />
                    {a.postedAt}
                  </div>
                </CardContent>
              </Card>
            ))}
          </div>

          {!showAll && MOCK_ANNOUNCEMENTS.length > 3 && (
            <div className="text-center mt-10">
              <Button
                variant="outline"
                size="lg"
                className="gap-2"
                onClick={() => setShowAll(true)}
              >
                Show More Announcements
                <ChevronDown className="h-4 w-4" />
              </Button>
            </div>
          )}

        </div>
      </div>
    </section>
  )
}

// ─────────────────────────────────────────────────────────────────────────────

export default function Home() {
  // Teacher features — one card per service that teachers interact with
  const teacherFeatures = [
    {
      icon: <LayoutDashboard className="h-7 w-7 text-primary-foreground" />,
      title: 'Create & Manage Classrooms',
      description:
        'Set up virtual classrooms, assign subjects, generate invite codes, and control active status — all from your dashboard.',
      service: 'Classroom Service',
    },
    {
      icon: <BookOpen className="h-7 w-7 text-primary-foreground" />,
      title: 'Upload Modules & Resources',
      description:
        'Organise your content into modules and upload PDFs, videos, or links for students to access at any time.',
      service: 'Resources Service',
    },
    {
      icon: <Megaphone className="h-7 w-7 text-primary-foreground" />,
      title: 'Post Announcements',
      description:
        'Keep your class informed with announcements. Create, edit, delete, and share updates directly in the classroom.',
      service: 'Announcement Service',
    },
    {
      icon: <ClipboardList className="h-7 w-7 text-primary-foreground" />,
      title: 'Create Quizzes',
      description:
        'Build quizzes, add questions, and automatically calculate student scores. Review attempt history at any time.',
      service: 'Quizzes Service',
    },
    {
      icon: <FileText className="h-7 w-7 text-primary-foreground" />,
      title: 'Manage Assignments',
      description:
        'Create assignments with due dates, review student file submissions, grade work, and provide written feedback.',
      service: 'Assignment Service',
    },
    {
      icon: <MessageSquare className="h-7 w-7 text-primary-foreground" />,
      title: 'Answer Student Questions',
      description:
        'Participate in the classroom Q&A forum. Reply to student questions and keep discussions on track.',
      service: 'Q&A Service',
    },
  ]

  // Student features — one card per service that students interact with
  const studentFeatures = [
    {
      icon: <KeyRound className="h-7 w-7 text-slate-100 dark:text-slate-800" />,
      title: 'Join Classes with Invite Code',
      description:
        'Enter the invite code given by your teacher to instantly enrol in a private classroom.',
      service: 'Enrollment Service',
    },
    {
      icon: <BookOpen className="h-7 w-7 text-slate-100 dark:text-slate-800" />,
      title: 'Access Learning Resources',
      description:
        'Browse modules and download or view PDFs, videos, and links uploaded by your teacher.',
      service: 'Resources Service',
    },
    {
      icon: <Megaphone className="h-7 w-7 text-slate-100 dark:text-slate-800" />,
      title: 'Stay Updated with Announcements',
      description:
        'Never miss important class updates — read announcements posted by your teacher in real time.',
      service: 'Announcement Service',
    },
    {
      icon: <ClipboardList className="h-7 w-7 text-slate-100 dark:text-slate-800" />,
      title: 'Take Quizzes',
      description:
        'Attempt quizzes set by your teacher and instantly see your calculated score and attempt history.',
      service: 'Quizzes Service',
    },
    {
      icon: <FileText className="h-7 w-7 text-slate-100 dark:text-slate-800" />,
      title: 'Submit Assignments',
      description:
        'Upload your assignment files, track submission status, and receive grades and feedback from your teacher.',
      service: 'Assignment Service',
    },
    {
      icon: <MessageSquare className="h-7 w-7 text-slate-100 dark:text-slate-800" />,
      title: 'Ask Questions & Discuss',
      description:
        'Post questions in the classroom Q&A forum, reply to classmates, and get answers from your teacher.',
      service: 'Q&A Service',
    },
  ]

  return (
    <div className="min-h-screen bg-background">

      {/* ── Navigation ── */}
      <nav className="border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60 sticky top-0 z-50">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <div className="flex items-center gap-2">
              <GraduationCap className="h-8 w-8 text-primary" />
              <span className="text-2xl font-bold text-foreground">OpenTutor</span>
            </div>
            <div className="flex items-center gap-3">
              {/* Profile Service — Login User */}
              <Link href="/login">
                <Button variant="ghost" size="lg" className="gap-2">
                  <LogIn className="h-4 w-4" />
                  Log In
                </Button>
              </Link>
              {/* Profile Service — Register User */}
              <Link href="/signup">
                <Button size="lg" className="gap-2">
                  <UserPlus className="h-4 w-4" />
                  Sign Up
                </Button>
              </Link>
            </div>
          </div>
        </div>
      </nav>

      {/* ── Hero ── */}
      <section className="relative overflow-hidden bg-gradient-to-b from-orange-50/50 to-background dark:from-orange-950/10">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-20 sm:py-32">
          <div className="max-w-4xl mx-auto text-center">
            <h1 className="text-4xl sm:text-5xl lg:text-6xl font-bold text-foreground mb-6 leading-tight">
              Teach and Learn.{' '}
              <span className="text-primary">Together.</span>
            </h1>
            <p className="text-lg sm:text-xl text-muted-foreground mb-8 max-w-2xl mx-auto">
              OpenTutor gives teachers the tools to run virtual classrooms — and students
              everything they need to learn, participate, and grow.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              {/* Profile Service — Register as Student */}
              <Link href="/signup?role=student">
                <Button size="lg" className="text-base px-8 py-6 shadow-lg hover:shadow-xl transition-all">
                  Start Learning
                </Button>
              </Link>
              {/* Profile Service — Register as Teacher */}
              <Link href="/signup?role=teacher">
                <Button size="lg" variant="outline" className="text-base px-8 py-6">
                  Start Teaching
                </Button>
              </Link>
            </div>
          </div>
        </div>
        {/* Decorative blobs */}
        <div className="absolute inset-0 -z-10 opacity-30 pointer-events-none">
          <div className="absolute top-20 left-10 w-72 h-72 bg-primary/20 rounded-full blur-3xl" />
          <div className="absolute bottom-20 right-10 w-96 h-96 bg-primary/10 rounded-full blur-3xl" />
        </div>
      </section>

      {/* ── How it works ── */}
      <section className="py-20 bg-background">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-14">
            <h2 className="text-3xl sm:text-4xl font-bold text-foreground mb-4">How it works</h2>
            <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
              Three simple steps to get started — whether you're here to teach or to learn.
            </p>
          </div>
          <div className="grid md:grid-cols-3 gap-8 max-w-5xl mx-auto">
            {/* Step 1 — Profile Service */}
            <div className="text-center">
              <div className="w-14 h-14 bg-primary/10 rounded-full flex items-center justify-center mx-auto mb-4">
                <UserCircle className="h-7 w-7 text-primary" />
              </div>
              <div className="text-xs font-semibold text-primary uppercase tracking-widest mb-2">Step 1 — Profile Service</div>
              <h3 className="text-lg font-bold mb-2">Create your account</h3>
              <p className="text-muted-foreground text-sm">
                Register with your name, email, and password. Choose your role — student or teacher — and you're in.
              </p>
            </div>
            {/* Step 2 — Classroom + Enrollment */}
            <div className="text-center">
              <div className="w-14 h-14 bg-primary/10 rounded-full flex items-center justify-center mx-auto mb-4">
                <LayoutDashboard className="h-7 w-7 text-primary" />
              </div>
              <div className="text-xs font-semibold text-primary uppercase tracking-widest mb-2">Step 2 — Classroom & Enrollment</div>
              <h3 className="text-lg font-bold mb-2">Set up or join a classroom</h3>
              <p className="text-muted-foreground text-sm">
                Teachers create a classroom and share an invite code. Students enter the code to enrol instantly.
              </p>
            </div>
            {/* Step 3 — All content services */}
            <div className="text-center">
              <div className="w-14 h-14 bg-primary/10 rounded-full flex items-center justify-center mx-auto mb-4">
                <BookOpen className="h-7 w-7 text-primary" />
              </div>
              <div className="text-xs font-semibold text-primary uppercase tracking-widest mb-2">Step 3 — All Services</div>
              <h3 className="text-lg font-bold mb-2">Teach, learn, and engage</h3>
              <p className="text-muted-foreground text-sm">
                Post resources, announcements, quizzes, and assignments. Students participate via Q&A, submissions, and quiz attempts.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* ── For Teachers ── */}
      <section className="py-20 bg-muted/30">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="max-w-6xl mx-auto">
            <div className="text-center mb-14">
              <div className="inline-flex items-center gap-2 bg-primary/10 text-primary px-4 py-2 rounded-full text-sm font-medium mb-4">
                <BookOpen className="h-4 w-4" />
                For Teachers
              </div>
              <h2 className="text-3xl sm:text-4xl font-bold text-foreground mb-4">
                Everything you need to run your classroom
              </h2>
              <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
                Each feature maps directly to a service in the platform.
              </p>
            </div>
            <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-6">
              {teacherFeatures.map((f) => (
                <Card key={f.title} className="bg-gradient-to-br from-orange-50 to-white dark:from-orange-950/20 dark:to-card hover:shadow-md transition-shadow">
                  <CardHeader>
                    <div className="w-14 h-14 bg-primary rounded-xl flex items-center justify-center mb-4">
                      {f.icon}
                    </div>
                    <CardTitle className="text-lg">{f.title}</CardTitle>
                    <CardDescription className="text-sm">{f.description}</CardDescription>
                  </CardHeader>
                </Card>
              ))}
            </div>
            <div className="text-center mt-10">
              <Link href="/signup?role=teacher">
                <Button size="lg" className="px-10">Get Started as a Teacher</Button>
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* ── For Students ── */}
      <section className="py-20 bg-background">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="max-w-6xl mx-auto">
            <div className="text-center mb-14">
              <div className="inline-flex items-center gap-2 bg-primary/10 text-primary px-4 py-2 rounded-full text-sm font-medium mb-4">
                <GraduationCap className="h-4 w-4" />
                For Students
              </div>
              <h2 className="text-3xl sm:text-4xl font-bold text-foreground mb-4">
                Everything you need to learn and participate
              </h2>
              <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
                Join a classroom with an invite code and access everything your teacher has set up.
              </p>
            </div>
            <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-6">
              {studentFeatures.map((f) => (
                <Card key={f.title} className="bg-gradient-to-br from-slate-50 to-white dark:from-slate-950/20 dark:to-card hover:shadow-md transition-shadow">
                  <CardHeader>
                    <div className="w-14 h-14 bg-slate-800 dark:bg-slate-200 rounded-xl flex items-center justify-center mb-4">
                      {f.icon}
                    </div>
                    <CardTitle className="text-lg">{f.title}</CardTitle>
                    <CardDescription className="text-sm">{f.description}</CardDescription>
                  </CardHeader>
                </Card>
              ))}
            </div>
            <div className="text-center mt-10">
              <Link href="/signup?role=student">
                <Button size="lg" className="px-10">Get Started as a Student</Button>
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* ── Latest Announcements — Announcement Service ── */}
      {/* TODO: GET /api/announcements/latest — fetch latest public announcements across all active classrooms */}
      <LatestAnnouncements />

      {/* ── CTA ── */}
      <section className="py-20 bg-gradient-to-br from-primary via-orange-500 to-orange-600 text-white">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="max-w-3xl mx-auto text-center">
            <h2 className="text-3xl sm:text-4xl font-bold mb-6">Ready to get started?</h2>
            <p className="text-xl opacity-90 mb-8">
              Create your free account today and start teaching or learning on OpenTutor.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              {/* Profile Service — Register as Student */}
              <Link href="/signup?role=student">
                <Button size="lg" variant="secondary" className="text-base px-8 py-6">
                  Sign Up as Student
                </Button>
              </Link>
              {/* Profile Service — Register as Teacher */}
              <Link href="/signup?role=teacher">
                <Button size="lg" variant="outline" className="text-base px-8 py-6 bg-white/10 hover:bg-white/20 text-white border-white/30">
                  Sign Up as Teacher
                </Button>
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* ── Footer ── */}
      <footer className="bg-muted py-10">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="max-w-6xl mx-auto flex flex-col md:flex-row justify-between items-center gap-6">
            <div className="flex items-center gap-2">
              <GraduationCap className="h-6 w-6 text-primary" />
              <span className="text-xl font-bold">OpenTutor</span>
            </div>
            <div className="flex gap-6 text-sm text-muted-foreground">
              <Link href="#" className="hover:text-primary transition-colors">About</Link>
              <Link href="#" className="hover:text-primary transition-colors">Privacy</Link>
              <Link href="#" className="hover:text-primary transition-colors">Terms</Link>
              <Link href="#" className="hover:text-primary transition-colors">Contact</Link>
            </div>
          </div>
          <div className="mt-8 pt-6 border-t text-center text-sm text-muted-foreground">
            © 2024 OpenTutor. All rights reserved.
          </div>
        </div>
      </footer>

    </div>
  )
}