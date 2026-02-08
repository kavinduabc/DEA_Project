'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Badge } from '@/components/ui/badge'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Textarea } from '@/components/ui/textarea'
import { ScrollArea } from '@/components/ui/scroll-area'
import { Separator } from '@/components/ui/separator'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from '@/components/ui/table'
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
  Users,
  BookOpen,
  FileText,
  Plus,
  Copy,
  Check,
  MessageSquare,
  Award,
  Settings,
  Calendar,
  Upload,
  Trash2,
  Edit
} from 'lucide-react'

export default function TeacherClassView({ params }: { params: { id: string } }) {
  const [copiedCode, setCopiedCode] = useState(false)
  const [activeTab, setActiveTab] = useState('stream')
  const [showAddModule, setShowAddModule] = useState(false)
  const [showAddAnnouncement, setShowAddAnnouncement] = useState(false)
  const [newAnnouncement, setNewAnnouncement] = useState({ title: '', content: '' })
  const [newModule, setNewModule] = useState({ title: '', description: '' })

  const classInfo = {
    id: params.id,
    title: 'Advanced Java Programming',
    description: 'Master advanced Java concepts including OOP, exception handling, and collections framework.',
    category: 'Programming',
    students: 234,
    modules: 12,
    inviteCode: 'JVA729',
    createdAt: '2 months ago'
  }

  const announcements = [
    {
      id: 1,
      title: 'Welcome to the Course!',
      content: 'Hello everyone! Welcome to Advanced Java Programming. I am excited to guide you through this journey of mastering Java.',
      author: 'Dr. Sarah Chen',
      date: '2 months ago',
      comments: 15
    },
    {
      id: 2,
      title: 'Module 4 Now Available',
      content: 'I have just published Module 4 on Advanced OOP Concepts. Please review the materials and complete the assignment by Friday.',
      author: 'Dr. Sarah Chen',
      date: '1 week ago',
      comments: 8
    },
    {
      id: 3,
      title: 'Assignment Reminder',
      content: 'Reminder that the polymorphism assignment is due this Wednesday. Make sure to submit before the deadline.',
      author: 'Dr. Sarah Chen',
      date: '2 days ago',
      comments: 3
    }
  ]

  const modules = [
    { id: 1, title: 'Getting Started', lessons: 2, resources: 3 },
    { id: 2, title: 'Java Basics Review', lessons: 3, resources: 2 },
    { id: 3, title: 'Object-Oriented Programming', lessons: 4, resources: 4 },
    { id: 4, title: 'Advanced OOP Concepts', lessons: 3, resources: 3 },
    { id: 5, title: 'Exception Handling', lessons: 2, resources: 2 },
    { id: 6, title: 'Collections Framework', lessons: 4, resources: 3 }
  ]

  const students = [
    { id: 1, name: 'John Doe', email: 'john@example.com', progress: 85, modules: 10, lastActive: '2 hours ago' },
    { id: 2, name: 'Emily Smith', email: 'emily@example.com', progress: 92, modules: 11, lastActive: '5 hours ago' },
    { id: 3, name: 'Michael Brown', email: 'michael@example.com', progress: 78, modules: 9, lastActive: '1 day ago' },
    { id: 4, name: 'Sarah Johnson', email: 'sarah@example.com', progress: 65, modules: 8, lastActive: '2 days ago' },
    { id: 5, name: 'David Wilson', email: 'david@example.com', progress: 88, modules: 10, lastActive: '3 hours ago' }
  ]

  const grades = [
    { id: 1, student: 'John Doe', assignment: 'OOP Quiz 1', score: 95, status: 'Graded', date: '2024-01-15' },
    { id: 2, student: 'Emily Smith', assignment: 'OOP Quiz 1', score: 98, status: 'Graded', date: '2024-01-15' },
    { id: 3, student: 'Michael Brown', assignment: 'OOP Quiz 1', score: 82, status: 'Graded', date: '2024-01-16' },
    { id: 4, student: 'Sarah Johnson', assignment: 'Polymorphism Project', score: '-', status: 'Pending', date: '2024-01-18' },
    { id: 5, student: 'David Wilson', assignment: 'Polymorphism Project', score: '-', status: 'Submitted', date: '2024-01-18' }
  ]

  const copyInviteCode = () => {
    navigator.clipboard.writeText(classInfo.inviteCode)
    setCopiedCode(true)
    setTimeout(() => setCopiedCode(false), 2000)
  }

  const handleAddAnnouncement = () => {
    console.log('Adding announcement:', newAnnouncement)
    setShowAddAnnouncement(false)
    setNewAnnouncement({ title: '', content: '' })
  }

  const handleAddModule = () => {
    console.log('Adding module:', newModule)
    setShowAddModule(false)
    setNewModule({ title: '', description: '' })
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

      {/* Class Header */}
      <div className="bg-gradient-to-r from-primary via-orange-500 to-orange-600 text-white py-8">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex flex-col lg:flex-row lg:items-start lg:justify-between gap-4">
            <div className="flex-1">
              <div className="flex items-center gap-2 mb-2">
                <Badge className="bg-white/20 text-white border-white/30">
                  {classInfo.category}
                </Badge>
              </div>
              <h1 className="text-3xl sm:text-4xl font-bold mb-2">{classInfo.title}</h1>
              <p className="opacity-90 text-lg">{classInfo.description}</p>
              <div className="flex flex-wrap gap-4 mt-4 text-sm">
                <div className="flex items-center gap-2">
                  <Users className="h-4 w-4" />
                  {classInfo.students} students
                </div>
                <div className="flex items-center gap-2">
                  <BookOpen className="h-4 w-4" />
                  {classInfo.modules} modules
                </div>
                <div className="flex items-center gap-2">
                  <Calendar className="h-4 w-4" />
                  Created {classInfo.createdAt}
                </div>
              </div>
            </div>
            <Card className="bg-white/10 backdrop-blur border-white/20 text-white">
              <CardContent className="p-4">
                <div className="text-center">
                  <p className="text-sm opacity-90 mb-2">Invite Code</p>
                  <div className="flex items-center gap-2">
                    <code className="text-3xl font-bold tracking-wider">
                      {classInfo.inviteCode}
                    </code>
                    <Button
                      variant="ghost"
                      size="icon"
                      className="h-8 w-8 text-white hover:bg-white/20"
                      onClick={copyInviteCode}
                    >
                      {copiedCode ? (
                        <Check className="h-5 w-5" />
                      ) : (
                        <Copy className="h-5 w-5" />
                      )}
                    </Button>
                  </div>
                </div>
              </CardContent>
            </Card>
          </div>
        </div>
      </div>

      {/* Main Content */}
      <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-6">
        <Tabs value={activeTab} onValueChange={setActiveTab} className="space-y-6">
          <TabsList className="grid w-full grid-cols-5">
            <TabsTrigger value="stream" className="gap-2">
              <MessageSquare className="h-4 w-4" />
              Stream
            </TabsTrigger>
            <TabsTrigger value="classwork" className="gap-2">
              <BookOpen className="h-4 w-4" />
              Classwork
            </TabsTrigger>
            <TabsTrigger value="people" className="gap-2">
              <Users className="h-4 w-4" />
              People
            </TabsTrigger>
            <TabsTrigger value="grades" className="gap-2">
              <Award className="h-4 w-4" />
              Grades
            </TabsTrigger>
            <TabsTrigger value="settings" className="gap-2">
              <Settings className="h-4 w-4" />
              Settings
            </TabsTrigger>
          </TabsList>

          {/* Stream Tab */}
          <TabsContent value="stream" className="space-y-6">
            <div className="flex justify-between items-center">
              <h2 className="text-2xl font-bold">Announcements</h2>
              <Dialog open={showAddAnnouncement} onOpenChange={setShowAddAnnouncement}>
                <DialogTrigger asChild>
                  <Button className="gap-2">
                    <Plus className="h-4 w-4" />
                    New Announcement
                  </Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-lg">
                  <DialogHeader>
                    <DialogTitle>Create Announcement</DialogTitle>
                    <DialogDescription>
                      Share important updates with your students
                    </DialogDescription>
                  </DialogHeader>
                  <div className="space-y-4 py-4">
                    <div className="space-y-2">
                      <Label htmlFor="title">Title</Label>
                      <Input
                        id="title"
                        placeholder="Announcement title"
                        value={newAnnouncement.title}
                        onChange={(e) => setNewAnnouncement({ ...newAnnouncement, title: e.target.value })}
                      />
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="content">Content</Label>
                      <Textarea
                        id="content"
                        placeholder="Write your announcement..."
                        value={newAnnouncement.content}
                        onChange={(e) => setNewAnnouncement({ ...newAnnouncement, content: e.target.value })}
                        rows={4}
                      />
                    </div>
                    <div className="flex gap-3 pt-2">
                      <Button
                        variant="outline"
                        className="flex-1"
                        onClick={() => setShowAddAnnouncement(false)}
                      >
                        Cancel
                      </Button>
                      <Button
                        className="flex-1"
                        onClick={handleAddAnnouncement}
                        disabled={!newAnnouncement.title || !newAnnouncement.content}
                      >
                        Post Announcement
                      </Button>
                    </div>
                  </div>
                </DialogContent>
              </Dialog>
            </div>

            <div className="space-y-4">
              {announcements.map((announcement) => (
                <Card key={announcement.id}>
                  <CardHeader>
                    <div className="flex items-start justify-between">
                      <div className="flex-1">
                        <CardTitle className="text-xl mb-1">{announcement.title}</CardTitle>
                        <CardDescription>
                          {announcement.author} • {announcement.date}
                        </CardDescription>
                      </div>
                      <Button variant="ghost" size="icon">
                        <Edit className="h-4 w-4" />
                      </Button>
                    </div>
                  </CardHeader>
                  <CardContent>
                    <p className="text-muted-foreground mb-4">{announcement.content}</p>
                    <div className="flex items-center gap-2 text-sm text-muted-foreground">
                      <MessageSquare className="h-4 w-4" />
                      {announcement.comments} comments
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>

          {/* Classwork Tab */}
          <TabsContent value="classwork" className="space-y-6">
            <div className="flex justify-between items-center">
              <h2 className="text-2xl font-bold">Modules & Content</h2>
              <Dialog open={showAddModule} onOpenChange={setShowAddModule}>
                <DialogTrigger asChild>
                  <Button className="gap-2">
                    <Plus className="h-4 w-4" />
                    Add Module
                  </Button>
                </DialogTrigger>
                <DialogContent className="sm:max-w-lg">
                  <DialogHeader>
                    <DialogTitle>Create Module</DialogTitle>
                    <DialogDescription>
                      Add a new module to your course
                    </DialogDescription>
                  </DialogHeader>
                  <div className="space-y-4 py-4">
                    <div className="space-y-2">
                      <Label htmlFor="moduleTitle">Module Title</Label>
                      <Input
                        id="moduleTitle"
                        placeholder="e.g., Advanced OOP Concepts"
                        value={newModule.title}
                        onChange={(e) => setNewModule({ ...newModule, title: e.target.value })}
                      />
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="moduleDescription">Description</Label>
                      <Textarea
                        id="moduleDescription"
                        placeholder="Describe what students will learn..."
                        value={newModule.description}
                        onChange={(e) => setNewModule({ ...newModule, description: e.target.value })}
                        rows={3}
                      />
                    </div>
                    <div className="flex gap-3 pt-2">
                      <Button
                        variant="outline"
                        className="flex-1"
                        onClick={() => setShowAddModule(false)}
                      >
                        Cancel
                      </Button>
                      <Button
                        className="flex-1"
                        onClick={handleAddModule}
                        disabled={!newModule.title}
                      >
                        Create Module
                      </Button>
                    </div>
                  </div>
                </DialogContent>
              </Dialog>
            </div>

            <div className="grid md:grid-cols-2 gap-4">
              {modules.map((module) => (
                <Card key={module.id} className="hover:shadow-md transition-shadow">
                  <CardHeader>
                    <div className="flex items-start justify-between">
                      <div className="flex-1">
                        <div className="flex items-center gap-2 mb-2">
                          <Badge>Module {module.id}</Badge>
                        </div>
                        <CardTitle className="text-xl">{module.title}</CardTitle>
                      </div>
                      <div className="flex gap-1">
                        <Button variant="ghost" size="icon">
                          <Edit className="h-4 w-4" />
                        </Button>
                        <Button variant="ghost" size="icon">
                          <Trash2 className="h-4 w-4 text-destructive" />
                        </Button>
                      </div>
                    </div>
                  </CardHeader>
                  <CardContent>
                    <div className="grid grid-cols-2 gap-4 text-sm">
                      <div className="flex items-center gap-2 text-muted-foreground">
                        <BookOpen className="h-4 w-4" />
                        <span>{module.lessons} lessons</span>
                      </div>
                      <div className="flex items-center gap-2 text-muted-foreground">
                        <FileText className="h-4 w-4" />
                        <span>{module.resources} resources</span>
                      </div>
                    </div>
                    <Separator className="my-4" />
                    <div className="flex gap-2">
                      <Button variant="outline" size="sm" className="gap-2 flex-1">
                        <Upload className="h-4 w-4" />
                        Upload PDF
                      </Button>
                      <Button variant="outline" size="sm" className="gap-2 flex-1">
                        <Plus className="h-4 w-4" />
                        Add Quiz
                      </Button>
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>

          {/* People Tab */}
          <TabsContent value="people" className="space-y-6">
            <div className="flex justify-between items-center">
              <h2 className="text-2xl font-bold">Enrolled Students ({students.length})</h2>
              <div className="flex gap-2">
                <Input placeholder="Search students..." className="w-64" />
              </div>
            </div>

            <Card>
              <CardContent className="p-0">
                <ScrollArea className="max-h-[600px]">
                  <Table>
                    <TableHeader>
                      <TableRow>
                        <TableHead>Student</TableHead>
                        <TableHead>Progress</TableHead>
                        <TableHead>Modules</TableHead>
                        <TableHead>Last Active</TableHead>
                        <TableHead className="text-right">Actions</TableHead>
                      </TableRow>
                    </TableHeader>
                    <TableBody>
                      {students.map((student) => (
                        <TableRow key={student.id}>
                          <TableCell>
                            <div>
                              <div className="font-medium">{student.name}</div>
                              <div className="text-sm text-muted-foreground">{student.email}</div>
                            </div>
                          </TableCell>
                          <TableCell>
                            <div className="flex items-center gap-2">
                              <div className="flex-1 h-2 bg-muted rounded-full overflow-hidden max-w-[100px]">
                                <div
                                  className="h-full bg-primary"
                                  style={{ width: `${student.progress}%` }}
                                />
                              </div>
                              <span className="text-sm font-medium">{student.progress}%</span>
                            </div>
                          </TableCell>
                          <TableCell>{student.modules} completed</TableCell>
                          <TableCell className="text-muted-foreground">{student.lastActive}</TableCell>
                          <TableCell className="text-right">
                            <Button variant="ghost" size="sm">View</Button>
                          </TableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </ScrollArea>
              </CardContent>
            </Card>
          </TabsContent>

          {/* Grades Tab */}
          <TabsContent value="grades" className="space-y-6">
            <div className="flex justify-between items-center">
              <h2 className="text-2xl font-bold">Grades & Assignments</h2>
              <Button className="gap-2">
                <Plus className="h-4 w-4" />
                Create Assignment
              </Button>
            </div>

            <Card>
              <CardContent className="p-0">
                <ScrollArea className="max-h-[600px]">
                  <Table>
                    <TableHeader>
                      <TableRow>
                        <TableHead>Student</TableHead>
                        <TableHead>Assignment</TableHead>
                        <TableHead>Score</TableHead>
                        <TableHead>Status</TableHead>
                        <TableHead>Date</TableHead>
                        <TableHead className="text-right">Actions</TableHead>
                      </TableRow>
                    </TableHeader>
                    <TableBody>
                      {grades.map((grade) => (
                        <TableRow key={grade.id}>
                          <TableCell className="font-medium">{grade.student}</TableCell>
                          <TableCell>{grade.assignment}</TableCell>
                          <TableCell>
                            {grade.score !== '-' ? (
                              <Badge variant="outline" className="font-semibold">
                                {grade.score}%
                              </Badge>
                            ) : (
                              <span className="text-muted-foreground">-</span>
                            )}
                          </TableCell>
                          <TableCell>
                            <Badge
                              variant={
                                grade.status === 'Graded'
                                  ? 'default'
                                  : grade.status === 'Submitted'
                                  ? 'secondary'
                                  : 'outline'
                              }
                            >
                              {grade.status}
                            </Badge>
                          </TableCell>
                          <TableCell className="text-muted-foreground">{grade.date}</TableCell>
                          <TableCell className="text-right">
                            <Button variant="ghost" size="sm">
                              {grade.status !== 'Graded' ? 'Grade' : 'View'}
                            </Button>
                          </TableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </ScrollArea>
              </CardContent>
            </Card>
          </TabsContent>

          {/* Settings Tab */}
          <TabsContent value="settings" className="space-y-6">
            <h2 className="text-2xl font-bold">Classroom Settings</h2>

            <div className="grid lg:grid-cols-2 gap-6">
              <Card>
                <CardHeader>
                  <CardTitle>General Information</CardTitle>
                  <CardDescription>
                    Update basic classroom details
                  </CardDescription>
                </CardHeader>
                <CardContent className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="classTitle">Class Title</Label>
                    <Input id="classTitle" defaultValue={classInfo.title} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="category">Category</Label>
                    <Input id="category" defaultValue={classInfo.category} />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="description">Description</Label>
                    <Textarea
                      id="description"
                      defaultValue={classInfo.description}
                      rows={4}
                    />
                  </div>
                  <Button className="w-full">Save Changes</Button>
                </CardContent>
              </Card>

              <Card>
                <CardHeader>
                  <CardTitle>Invite Code</CardTitle>
                  <CardDescription>
                    Manage your classroom's invite code
                  </CardDescription>
                </CardHeader>
                <CardContent className="space-y-4">
                  <div className="space-y-2">
                    <Label>Current Invite Code</Label>
                    <div className="flex items-center gap-2">
                      <Input
                        value={classInfo.inviteCode}
                        readOnly
                        className="font-mono text-2xl text-center uppercase tracking-widest"
                      />
                      <Button variant="outline" onClick={copyInviteCode}>
                        {copiedCode ? (
                          <Check className="h-4 w-4" />
                        ) : (
                          <Copy className="h-4 w-4" />
                        )}
                      </Button>
                    </div>
                  </div>
                  <Separator />
                  <Button variant="outline" className="w-full">
                    Generate New Code
                  </Button>
                </CardContent>
              </Card>

              <Card>
                <CardHeader>
                  <CardTitle>Class Management</CardTitle>
                  <CardDescription>
                    Manage classroom visibility and enrollment
                  </CardDescription>
                </CardHeader>
                <CardContent className="space-y-4">
                  <div className="flex items-center justify-between">
                    <div>
                      <div className="font-medium">Public Classroom</div>
                      <div className="text-sm text-muted-foreground">
                        Allow students to find and join this class
                      </div>
                    </div>
                    <Button variant="outline" size="sm">Toggle</Button>
                  </div>
                  <Separator />
                  <Button variant="destructive" className="w-full">
                    Delete Classroom
                  </Button>
                </CardContent>
              </Card>

              <Card>
                <CardHeader>
                  <CardTitle>Notifications</CardTitle>
                  <CardDescription>
                    Configure notification preferences
                  </CardDescription>
                </CardHeader>
                <CardContent className="space-y-4">
                  <div className="flex items-center justify-between">
                    <div>
                      <div className="font-medium">New Student Enrollments</div>
                      <div className="text-sm text-muted-foreground">
                        Get notified when new students join
                      </div>
                    </div>
                    <Button variant="outline" size="sm">Enabled</Button>
                  </div>
                  <div className="flex items-center justify-between">
                    <div>
                      <div className="font-medium">Assignment Submissions</div>
                      <div className="text-sm text-muted-foreground">
                        Get notified for new submissions
                      </div>
                    </div>
                    <Button variant="outline" size="sm">Enabled</Button>
                  </div>
                  <div className="flex items-center justify-between">
                    <div>
                      <div className="font-medium">Forum Activity</div>
                      <div className="text-sm text-muted-foreground">
                        Get notified for new forum posts
                      </div>
                    </div>
                    <Button variant="outline" size="sm">Disabled</Button>
                  </div>
                </CardContent>
              </Card>
            </div>
          </TabsContent>
        </Tabs>
      </div>
    </div>
  )
}
