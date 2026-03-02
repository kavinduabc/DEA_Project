'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card'
import { Badge } from '@/components/ui/badge'
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from '@/components/ui/dialog'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Textarea } from '@/components/ui/textarea'
import {
  GraduationCap,
  Plus,
  Users,
  BookOpen,
  TrendingUp,
  Clock,
  Settings,
  Copy,
  Check,
  X
} from 'lucide-react'

export default function TeacherDashboard() {
  const [showCreateModal, setShowCreateModal] = useState(false)
  const [copiedCode, setCopiedCode] = useState<string | null>(null)
  const [newClass, setNewClass] = useState({
    title: '',
    description: '',
    category: ''
  })

  const classrooms = [
    {
      id: 1,
      title: 'Advanced Java Programming',
      description: 'Master advanced Java concepts including OOP, exception handling, and collections.',
      category: 'Programming',
      students: 234,
      modules: 12,
      inviteCode: 'JVA729',
      createdAt: '2 months ago',
      activeAssignments: 3
    },
    {
      id: 2,
      title: 'Data Structures & Algorithms',
      description: 'Learn essential data structures and algorithmic problem-solving techniques.',
      category: 'Computer Science',
      students: 156,
      modules: 10,
      inviteCode: 'DSA483',
      createdAt: '1 month ago',
      activeAssignments: 2
    },
    {
      id: 3,
      title: 'Web Development Bootcamp',
      description: 'Complete full-stack web development with React, Node.js, and databases.',
      category: 'Web Development',
      students: 312,
      modules: 16,
      inviteCode: 'WEB956',
      createdAt: '3 weeks ago',
      activeAssignments: 5
    }
  ]

  const handleCreateClass = () => {
    console.log('Creating class:', newClass)
    setShowCreateModal(false)
    setNewClass({ title: '', description: '', category: '' })
  }

  const copyInviteCode = (code: string) => {
    navigator.clipboard.writeText(code)
    setCopiedCode(code)
    setTimeout(() => setCopiedCode(null), 2000)
  }

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
        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 mb-8">
          <div>
            <h1 className="text-3xl sm:text-4xl font-bold text-foreground mb-2">
              Welcome back, Sarah! 👋
            </h1>
            <p className="text-muted-foreground text-lg">
              Manage your classrooms and engage with students
            </p>
          </div>
          <Dialog open={showCreateModal} onOpenChange={setShowCreateModal}>
            <DialogTrigger asChild>
              <Button size="lg" className="gap-2 shadow-lg">
                <Plus className="h-5 w-5" />
                Create New Classroom
              </Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-lg">
              <DialogHeader>
                <DialogTitle>Create New Classroom</DialogTitle>
                <DialogDescription>
                  Set up a new classroom and start sharing your knowledge with students.
                </DialogDescription>
              </DialogHeader>
              <div className="space-y-4 py-4">
                <div className="space-y-2">
                  <Label htmlFor="title">Class Title</Label>
                  <Input
                    id="title"
                    placeholder="e.g., Advanced Java Programming"
                    value={newClass.title}
                    onChange={(e) => setNewClass({ ...newClass, title: e.target.value })}
                  />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="category">Category</Label>
                  <Input
                    id="category"
                    placeholder="e.g., Programming"
                    value={newClass.category}
                    onChange={(e) => setNewClass({ ...newClass, category: e.target.value })}
                  />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="description">Description</Label>
                  <Textarea
                    id="description"
                    placeholder="Describe what students will learn in your class..."
                    value={newClass.description}
                    onChange={(e) => setNewClass({ ...newClass, description: e.target.value })}
                    rows={4}
                  />
                </div>
                <div className="flex gap-3 pt-2">
                  <Button
                    variant="outline"
                    className="flex-1"
                    onClick={() => setShowCreateModal(false)}
                  >
                    Cancel
                  </Button>
                  <Button
                    className="flex-1"
                    onClick={handleCreateClass}
                    disabled={!newClass.title || !newClass.description}
                  >
                    Create Classroom
                  </Button>
                </div>
              </div>
            </DialogContent>
          </Dialog>
        </div>

        {/* Stats Overview */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Total Classrooms
              </CardTitle>
              <BookOpen className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">3</div>
              <p className="text-xs text-muted-foreground mt-1">Active courses</p>
            </CardContent>
          </Card>

          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Total Students
              </CardTitle>
              <Users className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">702</div>
              <p className="text-xs text-muted-foreground mt-1">Across all classes</p>
            </CardContent>
          </Card>

          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Active Assignments
              </CardTitle>
              <TrendingUp className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">10</div>
              <p className="text-xs text-muted-foreground mt-1">Pending reviews</p>
            </CardContent>
          </Card>

          <Card>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Total Modules
              </CardTitle>
              <BookOpen className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">38</div>
              <p className="text-xs text-muted-foreground mt-1">Content pieces</p>
            </CardContent>
          </Card>
        </div>

        {/* My Created Classrooms */}
        <div className="mb-6">
          <h2 className="text-2xl font-bold mb-4">My Created Classrooms</h2>
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
            {classrooms.map((classroom) => (
              <Card key={classroom.id} className="overflow-hidden hover:shadow-lg transition-shadow group">
                <Link href={`/teacher-dashboard/class/${classroom.id}`}>
                  <CardHeader>
                    <div className="flex items-start justify-between mb-2">
                      <Badge variant="outline">{classroom.category}</Badge>
                      <div className="opacity-0 group-hover:opacity-100 transition-opacity">
                        <Settings className="h-4 w-4 text-muted-foreground" />
                      </div>
                    </div>
                    <CardTitle className="text-xl group-hover:text-primary transition-colors">
                      {classroom.title}
                    </CardTitle>
                    <CardDescription className="line-clamp-2">
                      {classroom.description}
                    </CardDescription>
                  </CardHeader>
                  <CardContent className="space-y-3">
                    <div className="grid grid-cols-2 gap-4 text-sm">
                      <div className="flex items-center gap-2 text-muted-foreground">
                        <Users className="h-4 w-4" />
                        <span>{classroom.students} students</span>
                      </div>
                      <div className="flex items-center gap-2 text-muted-foreground">
                        <BookOpen className="h-4 w-4" />
                        <span>{classroom.modules} modules</span>
                      </div>
                      <div className="flex items-center gap-2 text-muted-foreground">
                        <Clock className="h-4 w-4" />
                        <span>{classroom.createdAt}</span>
                      </div>
                      <div className="flex items-center gap-2 text-muted-foreground">
                        <TrendingUp className="h-4 w-4" />
                        <span>{classroom.activeAssignments} assignments</span>
                      </div>
                    </div>
                  </CardContent>
                </Link>
                <CardFooter className="bg-muted/30">
                  <div className="flex items-center justify-between w-full">
                    <div className="flex items-center gap-2 text-sm">
                      <span className="text-muted-foreground">Invite Code:</span>
                      <code className="bg-background px-2 py-1 rounded font-mono font-semibold text-primary">
                        {classroom.inviteCode}
                      </code>
                    </div>
                    <Button
                      variant="ghost"
                      size="icon"
                      className="h-8 w-8"
                      onClick={() => copyInviteCode(classroom.inviteCode)}
                    >
                      {copiedCode === classroom.inviteCode ? (
                        <Check className="h-4 w-4 text-green-600" />
                      ) : (
                        <Copy className="h-4 w-4" />
                      )}
                    </Button>
                  </div>
                </CardFooter>
              </Card>
            ))}
          </div>
        </div>

        {/* Quick Actions */}
        <Card className="bg-gradient-to-br from-primary/10 via-orange-50/50 to-background dark:from-primary/5 dark:via-orange-950/10 dark:to-card">
          <CardHeader>
            <CardTitle>Quick Actions</CardTitle>
            <CardDescription>
              Common tasks to help you manage your classrooms
            </CardDescription>
          </CardHeader>
          <CardContent>
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
              <Button variant="outline" className="h-20 flex-col gap-2">
                <Plus className="h-6 w-6" />
                <span>Create Classroom</span>
              </Button>
              <Button variant="outline" className="h-20 flex-col gap-2">
                <Users className="h-6 w-6" />
                <span>Manage Students</span>
              </Button>
              <Button variant="outline" className="h-20 flex-col gap-2">
                <BookOpen className="h-6 w-6" />
                <span>Add Content</span>
              </Button>
              <Button variant="outline" className="h-20 flex-col gap-2">
                <TrendingUp className="h-6 w-6" />
                <span>View Analytics</span>
              </Button>
            </div>
          </CardContent>
        </Card>
      </main>
    </div>
  )
}
