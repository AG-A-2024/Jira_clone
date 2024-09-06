"use client";
import { useEffect, useState } from "react";
import {
  TableHead,
  TableRow,
  TableHeader,
  TableCell,
  TableBody,
  Table,
} from "@/components/ui/table";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

const API_URL = "http://localhost:8080/api/projects"; // Replace with your actual API URL
const BEARER_TOKEN =
  "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2ppcmFfY2xvbmUuY29tL2lzc3VlciIsInVwbiI6ImFkbWluQHBicy5lZHUucGwiLCJncm91cHMiOlsiQURNSU4iXSwiaWF0IjoxNzI1NjE2NzkxLCJleHAiOjE3MjU2MzQ3OTEsImp0aSI6Ijc3MjViNWFiLWVkNzctNDM3ZC05OTgwLWFhZmIyNTcwMjRhYSJ9.LoJr7FRYQKXcoR74ZCKTMK8L4EJ0xLhb3jsXP4oWlSn6Qun1X3zzgNKNhsCzKloti1ez9O1d14NH6R98WE8RcXll-v_-4YKkSfL-Mfb24zFLxLZDGL0MiAG3VBZDbHp7PJ5i_ANItQwaJ_38NogKLcaKiAusgaEslNAVBKp8r4die8uCeR6hUlnxpnKQ0xa0gk5ELvjIblHex3db9XSx93esW2ob-JHuRPc5lHs-QY6fNUaEfre0PwUKEJEb3lIg8S1V_Wc2_L5wNBRd_FGGtN6EWek-Q6bVJcWt08lNiWyx7slU8AZbodaJivSpgP8hozVwXlXPFM7uo4hJCVAbhQ";

type Project = {
  id: string;
  projectName: string;
  projectDescription: string;
  creationTime: string;
  releaseDate: string;
};

export default function Component() {
  const [projects, setProjects] = useState<Project[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentProject, setCurrentProject] = useState<Project | null>(null);

  useEffect(() => {
    async function fetchProjects() {
      try {
        const response = await fetch(API_URL, {
          headers: {
            Authorization: `Bearer ${BEARER_TOKEN}`,
          },
        });
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        setProjects(data);
      } catch (error) {
        setError(error.message);
      }
    }

    fetchProjects();
  }, []);

  async function handleDelete(projectId: string) {
    try {
      const response = await fetch(`${API_URL}/${projectId}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${BEARER_TOKEN}`,
        },
      });
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      setProjects((prevProjects) =>
        prevProjects.filter((project) => project.id !== projectId)
      );
    } catch (error) {
      setError(error.message);
    }
  }

  function handleEdit(project: Project) {
    setCurrentProject(project);
    setIsModalOpen(true);
  }

  async function handleUpdate(event: any) {
    event.preventDefault();
    const { id, name, description, created_at, defense_date } =
      event.target.elements;

    try {
      const response = await fetch(`${API_URL}/${currentProject?.id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${BEARER_TOKEN}`,
        },
        body: JSON.stringify({
          id: id.value,
          name: name.value,
          description: description.value,
          created_at: created_at.value,
          defense_date: defense_date.value,
        }),
      });
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const updatedProject = await response.json();
      setProjects((prevProjects) =>
        prevProjects.map((project) =>
          project.id === updatedProject.id ? updatedProject : project
        )
      );
      setIsModalOpen(false);
    } catch (error) {
      setError(error.message);
    }
  }

  return (
    <div className="flex flex-col gap-4 w-full">
      {error && <div className="text-red-500">{error}</div>}
      <div className="border rounded-lg">
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead className="w-[40px]">L.p.</TableHead>
              <TableHead>Id</TableHead>
              <TableHead>Nazwa</TableHead>
              <TableHead>Opis</TableHead>
              <TableHead className="w-[100px]">Utworzony</TableHead>
              <TableHead className="w-[100px]">Data obrony</TableHead>
              <TableHead className="w-[60px]">Edycja</TableHead>
              <TableHead className="w-[60px]">Usuń</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {projects.map((project, index) => (
              <TableRow key={project.id}>
                <TableCell className="w-[40px]">{index + 1}</TableCell>
                <TableCell className="font-medium">{project.id}</TableCell>
                <TableCell className="font-medium">
                  {project.projectName}
                </TableCell>
                <TableCell>{project.projectDescription}</TableCell>
                <TableCell className="w-[100px]">
                  {project.creationTime}
                </TableCell>
                <TableCell className="w-[100px]">
                  {project.releaseDate}
                </TableCell>
                <TableCell className="w-[60px]">
                  <Button
                    className="w-6 h-6"
                    size="icon"
                    variant="outline"
                    onClick={() => handleEdit(project)}
                  >
                    <Link2Icon className="w-4 h-4" />
                    <span className="sr-only">Edit</span>
                  </Button>
                </TableCell>
                <TableCell className="w-[60px]">
                  <Button
                    className="w-6 h-6"
                    size="icon"
                    variant="outline"
                    onClick={() => handleDelete(project.id)}
                  >
                    <TrashIcon className="w-4 h-4" />
                    <span className="sr-only">Delete</span>
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
      <div className="flex w-full items-center justify-between">
        <div className="flex items-center gap-2 text-sm">
          <Button className="h-8 w-8" size="icon" variant="ghost">
            1<span className="sr-only">Go to page 1</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            2<span className="sr-only">Go to page 2</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            3<span className="sr-only">Go to page 3</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            4<span className="sr-only">Go to page 4</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            5<span className="sr-only">Go to page 5</span>
          </Button>
        </div>
        <div className="flex items-center gap-2 text-sm">
          <span className="hidden md:flex h-8 items-center">
            Showing 1 to 10 of {projects.length} entries
          </span>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            <ChevronsLeftIcon className="w-4 h-4" />
            <span className="sr-only">Go to first page</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            <ChevronLeftIcon className="w-4 h-4" />
            <span className="sr-only">Go to previous page</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            <ChevronRightIcon className="w-4 h-4" />
            <span className="sr-only">Go to next page</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            <ChevronsRightIcon className="w-4 h-4" />
            <span className="sr-only">Go to last page</span>
          </Button>
        </div>
      </div>
      {isModalOpen && (
        <Dialog open={isModalOpen} onOpenChange={setIsModalOpen}>
          <DialogContent>
            <DialogHeader>
              <DialogTitle>Edit Project</DialogTitle>
            </DialogHeader>
            <form onSubmit={handleUpdate}>
              <div className="space-y-4">
                <div>
                  <Label htmlFor="id">Id</Label>
                  <Input
                    id="id"
                    name="id"
                    defaultValue={currentProject?.id}
                    readOnly
                  />
                </div>
                <div>
                  <Label htmlFor="name">Name</Label>
                  <Input
                    id="name"
                    name="name"
                    defaultValue={currentProject?.projectName}
                  />
                </div>
                <div>
                  <Label htmlFor="description">Description</Label>
                  <Input
                    id="description"
                    name="description"
                    defaultValue={currentProject?.projectDescription}
                  />
                </div>
                <div>
                  <Label htmlFor="created_at">Created At</Label>
                  <Input
                    id="created_at"
                    name="created_at"
                    defaultValue={currentProject?.creationTime}
                  />
                </div>
                <div>
                  <Label htmlFor="defense_date">Defense Date</Label>
                  <Input
                    id="defense_date"
                    name="defense_date"
                    defaultValue={currentProject?.releaseDate}
                  />
                </div>
              </div>
              <DialogFooter>
                <Button type="submit">Save</Button>
                <Button
                  type="button"
                  variant="outline"
                  onClick={() => setIsModalOpen(false)}
                >
                  Cancel
                </Button>
              </DialogFooter>
            </form>
          </DialogContent>
        </Dialog>
      )}
    </div>
  );
}

function ChevronLeftIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="m15 18-6-6 6-6" />
    </svg>
  );
}

function ChevronRightIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="m9 18 6-6-6-6" />
    </svg>
  );
}

function ChevronsLeftIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="m11 17-5-5 5-5" />
      <path d="m18 17-5-5 5-5" />
    </svg>
  );
}

function ChevronsRightIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="m6 17 5-5-5-5" />
      <path d="m13 17 5-5-5-5" />
    </svg>
  );
}

function Link2Icon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M9 17H7A5 5 0 0 1 7 7h2" />
      <path d="M15 7h2a5 5 0 1 1 0 10h-2" />
      <line x1="8" x2="16" y1="12" y2="12" />
    </svg>
  );
}

function TrashIcon(props: any) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <polyline points="3 6 5 6 21 6" />
      <path d="M19 6l-2 14a2 2 0 0 1-2 2H9a2 2 0 0 1-2-2L5 6" />
      <path d="M10 11v6" />
      <path d="M14 11v6" />
      <path d="M5 6V4a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v2" />
    </svg>
  );
}