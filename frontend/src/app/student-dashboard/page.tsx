'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Progress } from '@/components/ui/progress'
import { Badge } from '@/components/ui/badge'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import {
  GraduationCap,
  Search,
  Plus,
  Users,
  BookOpen,
  TrendingUp,
  Clock,
  CheckCircle2,
  ArrowRight,
  X
} from 'lucide-react'

export default function StudentDashboard() {
  const [showJoinModal, setShowJoinModal] = useState(false)
  const [inviteCode, setInviteCode] = useState('')
  const [searchQuery, setSearchQuery] = useState('')

  const handleJoinClass = () => {
    console.log('Joining class with code:', inviteCode)
    setShowJoinModal(false)
    setInviteCode('')
  }

  const enrolledClasses = [
    {
      id: 1,
      title: 'Advanced Java Programming',
      teacher: 'Dr. Sarah Chen',
      progress: 75,
      modules: 12,
      completed: 9,
      students: 234,
      color: 'bg-orange-500'
    },
    {
      id: 2,
      title: 'Mathematics: Calculus Fundamentals',
      teacher: 'Prof. Michael Brown',
      progress: 42,
      modules: 10,
      completed: 4,
      students: 189,
      color: 'bg-blue-500'
    },
    {
      id: 3,
      title: 'Science: Physics for Beginners',
      teacher: 'Dr. Emily Davis',
      progress: 90,
      modules: 8,
      completed: 7,
      students: 312,
      color: 'bg-green-500'
    }
  ]

  const publicClasses = [
    {
      id: 4,
      title: 'Python for Data Science',
      teacher: 'James Wilson',
      description: 'Learn Python from scratch and apply it to data analysis and visualization.',
      level: 'Beginner',
      duration: '8 weeks',
      students: 456,
      rating: 4.8
    },
    {
      id: 5,
      title: 'Web Development with React',
      teacher: 'Lisa Anderson',
      description: 'Master modern web development with React, Next.js, and Tailwind CSS.',
      level: 'Intermediate',
      duration: '12 weeks',
      students: 324,
      rating: 4.9
    },
    {
      id: 6,
      title: 'Creative Writing Workshop',
      teacher: 'Mark Thompson',
      description: 'Develop your storytelling skills through practical exercises and feedback.',
      level: 'All Levels',
      duration: '6 weeks',
      students: 156,
      rating: 4.7
    },
    {
      id: 7,
      title: 'Digital Marketing Essentials',
      teacher: 'Sarah Martinez',
      description: 'Learn SEO, social media marketing, and content strategy for the modern web.',
      level: 'Beginner',
      duration: '10 weeks',
      students: 289,
      rating: 4.6
    }
  ]

  return (
    <div className="min-h-screen bg-background">
      {/* Navigation */}
      <nav className="border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60 sticky top-0 z-40">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <Link href="/" className="flex items-center gap-2">
              <GraduationCap className="h-8 w-8 text-primary" />
              <span className="text-2xl font-bold text-foreground">OpenTutor</span>
            </Link>
            <div className="flex items-center gap-3">
              <Dialog open={showJoinModal} onOpenChange={setShowJoinModal}>
                <DialogTrigger asChild>
                  <Button size="lg" className="gap-2">
                    <Plus className="h-4 w-4" />
                    Join Private Class
                  </Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-md">
                  <DialogHeader>
                    <DialogTitle>Join a Private Class</DialogTitle>
                    <DialogDescription>
                      Enter the 6-digit invite code provided by your teacher to join their classroom.
                    </DialogDescription>
                  </DialogHeader>
                  <div className="space-y-4 py-4">
                    <div className="space-y-2">
                      <label htmlFor="inviteCode" className="text-sm font-medium">
                        6-Digit Invite Code
                      </label>
                      <Input
                        id="inviteCode"
                        placeholder="ABC123"
                        value={inviteCode}
                        onChange={(e) => setInviteCode(e.target.value.toUpperCase())}
                        maxLength={6}
                        className="text-center text-2xl tracking-widest uppercase"
                      />
                    </div>
                    <div className="flex gap-3">
                      <Button
                        variant="outline"
                        className="flex-1"
                        onClick={() => setShowJoinModal(false)}
                      >
                        Cancel
                      </Button>
                      <Button
                        className="flex-1"
                        onClick={handleJoinClass}
                        disabled={inviteCode.length !== 6}
                      >
                        Join
                      </Button>
                    </div>
                  </div>
                </DialogContent>
              </Dialog>
              <Link href="/">
                <Button variant="ghost" size="icon">
                  <X className="h-5 w-5" />
                </Button>
              </Link>
            </div>
          </div>
        </div>
      </nav>

      {/* Main Content */}
      <main className="container mx-auto px-4 sm:px-6 lg:px-8 py-8">
        {/* Welcome Section */}
        <div className="mb-8">
          <h1 className="text-3xl sm:text-4xl font-bold text-foreground mb-2">
            Welcome back, Alex! 👋
          </h1>
          <p className="text-muted-foreground text-lg">
            Continue your learning journey
          </p>
        </div>

        {/* Stats Overview */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Enrolled Courses
              </CardTitle>
              <BookOpen className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">3</div>
              <p className="text-xs text-muted-foreground mt-1">Active classes</p>
            </CardContent>
          </Card>

          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Total Progress
              </CardTitle>
              <TrendingUp className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">69%</div>
              <p className="text-xs text-muted-foreground mt-1">Across all courses</p>
            </CardContent>
          </Card>

          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Completed Modules
              </CardTitle>
              <CheckCircle2 className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">20</div>
              <p className="text-xs text-muted-foreground mt-1">Out of 30</p>
            </CardContent>
          </Card>

          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Time Spent
              </CardTitle>
              <Clock className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">48h</div>
              <p className="text-xs text-muted-foreground mt-1">This month</p>
            </CardContent>
          </Card>
        </div>

        {/* Main Tabs */}
        <Tabs defaultValue="enrollments" className="space-y-6">
          <TabsList className="grid w-full grid-cols-2 lg:w-96">
            <TabsTrigger value="enrollments" className="gap-2">
              <BookOpen className="h-4 w-4" />
              My Enrollments
            </TabsTrigger>
            <TabsTrigger value="find" className="gap-2">
              <Search className="h-4 w-4" />
              Find Classes
            </TabsTrigger>
          </TabsList>

          {/* My Enrollments Tab */}
          <TabsContent value="enrollments" className="space-y-6">
            <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
              {enrolledClasses.map((course) => (
                <Card key={course.id} className="overflow-hidden hover:shadow-lg transition-shadow cursor-pointer group">
                  <Link href={`/student-dashboard/class/${course.id}`}>
                    <CardHeader>
                      <div className={`h-2 ${course.color}`} />
                      <CardTitle className="text-xl mt-4 group-hover:text-primary transition-colors">
                        {course.title}
                      </CardTitle>
                      <CardDescription className="flex items-center gap-2">
                        <Users className="h-4 w-4" />
                        {course.teacher} • {course.students} students
                      </CardDescription>
                    </CardHeader>
                    <CardContent>
                      <div className="space-y-2">
                        <div className="flex items-center justify-between text-sm">
                          <span className="text-muted-foreground">Progress</span>
                          <span className="font-semibold text-primary">{course.progress}%</span>
                        </div>
                        <Progress value={course.progress} className="h-2" />
                        <div className="flex items-center justify-between text-sm text-muted-foreground pt-2">
                          <span>{course.completed}/{course.modules} modules</span>
                          <ArrowRight className="h-4 w-4 group-hover:translate-x-1 transition-transform" />
                        </div>
                      </div>
                    </CardContent>
                  </Link>
                </Card>
              ))}
            </div>
          </TabsContent>

          {/* Find Classes Tab */}
          <TabsContent value="find" className="space-y-6">
            {/* Search Bar */}
            <div className="max-w-2xl mx-auto mb-8">
              <div className="relative">
                <Search className="absolute left-4 top-1/2 -translate-y-1/2 h-5 w-5 text-muted-foreground" />
                <Input
                  type="text"
                  placeholder="Search for Java, Math, Science..."
                  className="pl-12 h-14 text-lg"
                  value={searchQuery}
                  onChange={(e) => setSearchQuery(e.target.value)}
                />
                <Button size="lg" className="absolute right-2 top-2">
                  Search
                </Button>
              </div>
            </div>

            {/* Filters */}
            <div className="flex flex-wrap gap-3 mb-6">
              <Button variant="outline" size="sm">All Levels</Button>
              <Button variant="outline" size="sm">Beginner</Button>
              <Button variant="outline" size="sm">Intermediate</Button>
              <Button variant="outline" size="sm">Advanced</Button>
              <Badge variant="secondary" className="h-8 px-3">Popular</Badge>
              <Badge variant="secondary" className="h-8 px-3">New</Badge>
            </div>

            {/* Public Classes Grid */}
            <div className="grid md:grid-cols-2 gap-6">
              {publicClasses.map((course) => (
                <Card key={course.id} className="hover:shadow-lg transition-shadow">
                  <CardHeader>
                    <div className="flex items-start justify-between mb-2">
                      <Badge variant="outline">{course.level}</Badge>
                      <div className="flex items-center gap-1 text-sm text-muted-foreground">
                        <span className="text-primary font-semibold">★</span>
                        {course.rating}
                      </div>
                    </div>
                    <CardTitle className="text-2xl">{course.title}</CardTitle>
                    <CardDescription className="flex items-center gap-4">
                      <span>{course.teacher}</span>
                      <span className="flex items-center gap-1">
                        <Clock className="h-4 w-4" />
                        {course.duration}
                      </span>
                    </CardDescription>
                  </CardHeader>
                  <CardContent>
                    <p className="text-muted-foreground mb-4">
                      {course.description}
                    </p>
                    <div className="flex items-center justify-between">
                      <span className="text-sm text-muted-foreground flex items-center gap-2">
                        <Users className="h-4 w-4" />
                        {course.students} students
                      </span>
                      <Button size="sm">
                        View Details
                      </Button>
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>
        </Tabs>
      </main>
    </div>
  )
}
