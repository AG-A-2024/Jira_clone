import { TableHead, TableRow, TableHeader, TableCell, TableBody, Table } from "@/components/ui/table"
import { Button } from "@/components/ui/button"

export default function Component() {
  return (
    <div className="flex flex-col gap-4 w-full">
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
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow>
              <TableCell className="w-[40px]">1</TableCell>
              <TableCell className="font-medium">PRJ001</TableCell>
              <TableCell className="font-medium">Project Apollo</TableCell>
              <TableCell>Initial project for Apollo program</TableCell>
              <TableCell className="w-[100px]">2023-01-01</TableCell>
              <TableCell className="w-[100px]">2023-06-30</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[40px]">2</TableCell>
              <TableCell className="font-medium">PRJ002</TableCell>
              <TableCell className="font-medium">Project Gemini</TableCell>
              <TableCell>Follow-up project after Apollo</TableCell>
              <TableCell className="w-[100px]">2023-02-14</TableCell>
              <TableCell className="w-[100px]">2023-12-31</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[40px]">3</TableCell>
              <TableCell className="font-medium">PRJ003</TableCell>
              <TableCell className="font-medium">Project Artemis</TableCell>
              <TableCell>Sustainable lunar exploration with commercial and international partners</TableCell>
              <TableCell className="w-[100px]">2024-11-01</TableCell>
              <TableCell className="w-[100px]">2025-12-31</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[40px]">4</TableCell>
              <TableCell className="font-medium">PRJ004</TableCell>
              <TableCell className="font-medium">Project Viking</TableCell>
              <TableCell>Mars lander and orbiter mission to search for biosignatures</TableCell>
              <TableCell className="w-[100px]">2025-05-01</TableCell>
              <TableCell className="w-[100px]">2026-12-31</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[40px]">5</TableCell>
              <TableCell className="font-medium">PRJ005</TableCell>
              <TableCell className="font-medium">Project Horizon</TableCell>
              <TableCell>Advanced lunar base construction and long-duration missions</TableCell>
              <TableCell className="w-[100px]">2026-09-01</TableCell>
              <TableCell className="w-[100px]">2027-12-31</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[40px]">6</TableCell>
              <TableCell className="font-medium">PRJ006</TableCell>
              <TableCell className="font-medium">Project Odyssey</TableCell>
              <TableCell>Interstellar probe to study exoplanets in the Alpha Centauri system</TableCell>
              <TableCell className="w-[100px]">2027-11-01</TableCell>
              <TableCell className="w-[100px]">2028-12-31</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[40px]">7</TableCell>
              <TableCell className="font-medium">PRJ007</TableCell>
              <TableCell className="font-medium">Project Phoenix</TableCell>
              <TableCell>Advanced AI research for human-robot collaboration in space</TableCell>
              <TableCell className="w-[100px]">2028-02-01</TableCell>
              <TableCell className="w-[100px]">2029-12-31</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[40px]">8</TableCell>
              <TableCell className="font-medium">PRJ008</TableCell>
              <TableCell className="font-medium">Project Aurora</TableCell>
              <TableCell>Solar energy satellite constellation for clean energy generation</TableCell>
              <TableCell className="w-[100px]">2029-07-01</TableCell>
              <TableCell className="w-[100px]">2030-12-31</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[40px]">9</TableCell>
              <TableCell className="font-medium">PRJ009</TableCell>
              <TableCell className="font-medium">Project Atlas</TableCell>
              <TableCell>Global climate monitoring satellite constellation for environmental research</TableCell>
              <TableCell className="w-[100px]">2030-09-01</TableCell>
              <TableCell className="w-[100px]">2031-12-31</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell className="w-[40px]">10</TableCell>
              <TableCell className="font-medium">PRJ010</TableCell>
              <TableCell className="font-medium">Project Horizon</TableCell>
              <TableCell>Advanced lunar base construction and long-duration missions</TableCell>
              <TableCell className="w-[100px]">2031-01-01</TableCell>
              <TableCell className="w-[100px]">2032-12-31</TableCell>
              <TableCell className="w-[60px]">
                <Button className="w-6 h-6" size="icon" variant="outline">
                  <Link2Icon className="w-4 h-4" />
                  <span className="sr-only">Edit</span>
                </Button>
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </div>
      <div className="flex w-full items-center justify-between">
        <div className="flex items-center gap-2 text-sm">
          <Button className="h-8 w-8" size="icon" variant="ghost">
            1
            <span className="sr-only">Go to page 1</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            2
            <span className="sr-only">Go to page 2</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            3
            <span className="sr-only">Go to page 3</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            4
            <span className="sr-only">Go to page 4</span>
          </Button>
          <Button className="h-8 w-8" size="icon" variant="ghost">
            5
            <span className="sr-only">Go to page 5</span>
          </Button>
        </div>
        <div className="flex items-center gap-2 text-sm">
          <span className="hidden md:flex h-8 items-center">Showing 1 to 10 of 100 entries</span>
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
    </div>
  )
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
  )
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
  )
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
  )
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
  )
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
  )
}