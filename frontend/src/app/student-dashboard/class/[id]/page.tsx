'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Button } from '@/components/ui/button'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Progress } from '@/components/ui/progress'
import { Badge } from '@/components/ui/badge'
import { ScrollArea } from '@/components/ui/scroll-area'
import { Separator } from '@/components/ui/separator'
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs'
import {
  GraduationCap,
  ArrowLeft,
  Users,
  BookOpen,
  FileText,
  Video,
  MessageSquare,
  Clock,
  CheckCircle2,
  Circle,
  ChevronRight,
  Play,
  Download
} from 'lucide-react'

export default function StudentClassView({ params }: { params: { id: string } }) {
  const [activeModule, setActiveModule] = useState(1)
  const [activeTab, setActiveTab] = useState('content')

  const classInfo = {
    id: params.id,
    title: 'Advanced Java Programming',
    teacher: 'Dr. Sarah Chen',
    students: 234,
    overallProgress: 75,
    modulesCompleted: 9,
    totalModules: 12
  }

  const modules = [
    {
      id: 1,
      title: 'Getting Started',
      status: 'completed',
      lessons: [
        { id: 1, title: 'Course Introduction', type: 'video', duration: '12:30' },
        { id: 2, title: 'Setting Up Your Environment', type: 'video', duration: '18:45' }
      ]
    },
    {
      id: 2,
      title: 'Java Basics Review',
      status: 'completed',
      lessons: [
        { id: 3, title: 'Variables and Data Types', type: 'video', duration: '22:15' },
        { id: 4, title: 'Control Flow', type: 'video', duration: '25:00' }
      ]
    },
    {
      id: 3,
      title: 'Object-Oriented Programming',
      status: 'completed',
      lessons: [
        { id: 5, title: 'Classes and Objects', type: 'video', duration: '30:00' },
        { id: 6, title: 'Inheritance', type: 'video', duration: '28:30' }
      ]
    },
    {
      id: 4,
      title: 'Advanced OOP Concepts',
      status: 'in_progress',
      lessons: [
        { id: 7, title: 'Polymorphism', type: 'video', duration: '32:00' },
        { id: 8, title: 'Abstract Classes', type: 'video', duration: '25:45' },
        { id: 9, title: 'Interfaces', type: 'video', duration: '27:30' }
      ]
    },
    {
      id: 5,
      title: 'Exception Handling',
      status: 'locked',
      lessons: [
        { id: 10, title: 'Try-Catch Blocks', type: 'video', duration: '20:00' },
        { id: 11, title: 'Custom Exceptions', type: 'video', duration: '18:30' }
      ]
    },
    {
      id: 6,
      title: 'Collections Framework',
      status: 'locked',
      lessons: [
        { id: 12, title: 'Lists', type: 'video', duration: '35:00' },
        { id: 13, title: 'Sets and Maps', type: 'video', duration: '40:00' }
      ]
    }
  ]

  const resources = [
    { id: 1, title: 'Java Cheat Sheet', type: 'pdf', size: '2.4 MB' },
    { id: 2, title: 'Practice Exercises', type: 'pdf', size: '1.8 MB' },
    { id: 3, title: 'Code Templates', type: 'zip', size: '560 KB' },
    { id: 4, title: 'Additional Reading', type: 'pdf', size: '3.2 MB' }
  ]

  const forumPosts = [
    { id: 1, author: 'John D.', title: 'Question about polymorphism', replies: 5, time: '2 hours ago' },
    { id: 2, author: 'Emily R.', title: 'Help with interfaces', replies: 8, time: '5 hours ago' },
    { id: 3, author: 'Mike S.', title: 'Best practices for collections', replies: 12, time: '1 day ago' }
  ]

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

      {/* Class Header */}
      <div className="bg-gradient-to-r from-primary via-orange-500 to-orange-600 text-white py-8">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8">
          <h1 className="text-3xl sm:text-4xl font-bold mb-2">{classInfo.title}</h1>
          <p className="opacity-90 mb-4">Dr. Sarah Chen</p>
          <div className="flex flex-wrap gap-4 text-sm">
            <div className="flex items-center gap-2">
              <Users className="h-4 w-4" />
              {classInfo.students} students
            </div>
            <div className="flex items-center gap-2">
              <BookOpen className="h-4 w-4" />
              {classInfo.modulesCompleted}/{classInfo.totalModules} modules
            </div>
            <div className="flex items-center gap-2">
              <CheckCircle2 className="h-4 w-4" />
              {classInfo.overallProgress}% complete
            </div>
          </div>
          <div className="mt-4 max-w-md">
            <Progress value={classInfo.overallProgress} className="h-2 bg-white/30" />
          </div>
        </div>
      </div>

      {/* Main Content */}
      <div className="container mx-auto px-4 sm:px-6 lg:px-8 py-6">
        <div className="grid lg:grid-cols-4 gap-6">
          {/* Sidebar - Modules & Resources */}
          <div className="lg:col-span-1">
            <Card className="sticky top-24">
              <CardHeader>
                <CardTitle className="text-lg">Course Content</CardTitle>
              </CardHeader>
              <CardContent className="p-0">
                <ScrollArea className="h-[calc(100vh-250px)]">
                  <div className="p-4 space-y-2">
                    {modules.map((module) => (
                      <div key={module.id}>
                        <button
                          onClick={() => setActiveModule(module.id)}
                          className={`w-full text-left p-3 rounded-lg transition-all ${
                            activeModule === module.id
                              ? 'bg-primary text-primary-foreground'
                              : 'bg-muted hover:bg-muted/80'
                          }`}
                          disabled={module.status === 'locked'}
                        >
                          <div className="flex items-start justify-between gap-2">
                            <div className="flex items-start gap-3">
                              {module.status === 'completed' ? (
                                <CheckCircle2 className="h-5 w-5 mt-0.5 flex-shrink-0" />
                              ) : module.status === 'in_progress' ? (
                                <Circle className="h-5 w-5 mt-0.5 flex-shrink-0" />
                              ) : (
                                <div className="h-5 w-5 mt-0.5 flex-shrink-0 rounded-full border-2 border-muted-foreground/30" />
                              )}
                              <div>
                                <div className="font-semibold text-sm">Module {module.id}</div>
                                <div className="text-xs opacity-90">{module.title}</div>
                              </div>
                            </div>
                            {module.status !== 'locked' && (
                              <ChevronRight className="h-4 w-4 opacity-70" />
                            )}
                          </div>
                        </button>
                        {activeModule === module.id && (
                          <div className="ml-8 mt-2 space-y-1">
                            {module.lessons.map((lesson) => (
                              <div key={lesson.id} className="flex items-center gap-2 text-sm p-2 rounded hover:bg-muted/50 cursor-pointer">
                                <Video className="h-3 w-3 opacity-70" />
                                <span className="flex-1 truncate">{lesson.title}</span>
                                <span className="text-xs opacity-60">{lesson.duration}</span>
                              </div>
                            ))}
                          </div>
                        )}
                      </div>
                    ))}
                  </div>
                  
                  <Separator className="my-4" />
                  
                  <div className="px-4 pb-4">
                    <h3 className="font-semibold mb-3">Resources</h3>
                    <div className="space-y-2">
                      {resources.map((resource) => (
                        <div
                          key={resource.id}
                          className="flex items-center justify-between p-2 rounded hover:bg-muted/50 cursor-pointer text-sm"
                        >
                          <div className="flex items-center gap-2">
                            <FileText className="h-4 w-4 text-primary" />
                            <span className="truncate">{resource.title}</span>
                          </div>
                          <Download className="h-4 w-4 opacity-60" />
                        </div>
                      ))}
                    </div>
                  </div>
                </ScrollArea>
              </CardContent>
            </Card>
          </div>

          {/* Main Content Area */}
          <div className="lg:col-span-3">
            <Tabs value={activeTab} onValueChange={setActiveTab} className="space-y-4">
              <TabsList className="grid w-full grid-cols-3">
                <TabsTrigger value="content" className="gap-2">
                  <Play className="h-4 w-4" />
                  Content
                </TabsTrigger>
                <TabsTrigger value="resources" className="gap-2">
                  <FileText className="h-4 w-4" />
                  Resources
                </TabsTrigger>
                <TabsTrigger value="forum" className="gap-2">
                  <MessageSquare className="h-4 w-4" />
                  Forum
                </TabsTrigger>
              </TabsList>

              {/* Content Tab */}
              <TabsContent value="content" className="space-y-6">
                <Card>
                  <CardHeader>
                    <div className="flex items-center gap-2 mb-2">
                      <Badge>Module 4</Badge>
                      <Badge variant="outline">In Progress</Badge>
                    </div>
                    <CardTitle className="text-2xl">Advanced OOP Concepts</CardTitle>
                    <CardDescription>
                      Deep dive into polymorphism, abstract classes, and interfaces
                    </CardDescription>
                  </CardHeader>
                  <CardContent>
                    <div className="aspect-video bg-muted rounded-lg mb-4 flex items-center justify-center">
                      <div className="text-center">
                        <Play className="h-16 w-16 mx-auto mb-4 text-primary opacity-80" />
                        <p className="text-muted-foreground">Video Player Placeholder</p>
                        <p className="text-sm text-muted-foreground mt-2">Duration: 32:00</p>
                      </div>
                    </div>
                    <div className="flex justify-between items-center">
                      <Button variant="outline" size="lg" className="gap-2">
                        <ArrowLeft className="h-4 w-4" />
                        Previous
                      </Button>
                      <Button size="lg" className="gap-2">
                        Next
                        <ChevronRight className="h-4 w-4" />
                      </Button>
                    </div>
                  </CardContent>
                </Card>

                <Card>
                  <CardHeader>
                    <CardTitle>Lesson Notes</CardTitle>
                  </CardHeader>
                  <CardContent>
                    <p className="text-muted-foreground mb-4">
                      Polymorphism is the ability of an object to take on many forms. In Java, this is achieved through method overriding and interface implementation.
                    </p>
                    <p className="text-muted-foreground">
                      Key concepts covered in this module include runtime polymorphism, compile-time polymorphism, and the use of abstract classes and interfaces to create flexible code architectures.
                    </p>
                  </CardContent>
                </Card>
              </TabsContent>

              {/* Resources Tab */}
              <TabsContent value="resources" className="space-y-4">
                <Card>
                  <CardHeader>
                    <CardTitle>Course Resources</CardTitle>
                    <CardDescription>
                      Download materials and additional resources to support your learning
                    </CardDescription>
                  </CardHeader>
                  <CardContent className="space-y-4">
                    {resources.map((resource) => (
                      <div
                        key={resource.id}
                        className="flex items-center justify-between p-4 border rounded-lg hover:border-primary transition-colors"
                      >
                        <div className="flex items-center gap-4">
                          <div className="h-12 w-12 bg-primary/10 rounded-lg flex items-center justify-center">
                            <FileText className="h-6 w-6 text-primary" />
                          </div>
                          <div>
                            <div className="font-semibold">{resource.title}</div>
                            <div className="text-sm text-muted-foreground">
                              {resource.type.toUpperCase()} • {resource.size}
                            </div>
                          </div>
                        </div>
                        <Button variant="outline" size="sm" className="gap-2">
                          <Download className="h-4 w-4" />
                          Download
                        </Button>
                      </div>
                    ))}
                  </CardContent>
                </Card>
              </TabsContent>

              {/* Forum Tab */}
              <TabsContent value="forum" className="space-y-4">
                <div className="flex justify-between items-center mb-4">
                  <h2 className="text-2xl font-bold">Class Discussion</h2>
                  <Button className="gap-2">
                    <MessageSquare className="h-4 w-4" />
                    New Post
                  </Button>
                </div>
                
                {forumPosts.map((post) => (
                  <Card key={post.id} className="hover:shadow-md transition-shadow cursor-pointer">
                    <CardHeader>
                      <div className="flex items-start justify-between">
                        <div>
                          <CardTitle className="text-lg mb-1">{post.title}</CardTitle>
                          <CardDescription>
                            by {post.author} • {post.time}
                          </CardDescription>
                        </div>
                        <Badge variant="secondary">{post.replies} replies</Badge>
                      </div>
                    </CardHeader>
                  </Card>
                ))}
              </TabsContent>
            </Tabs>
          </div>
        </div>
      </div>
    </div>
  )
}
