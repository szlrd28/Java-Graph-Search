import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AlgorithmsMentor extends ColorMaker implements ActionListener {

    Cells jelenlegi;
    public final static int fel=8, le=2, jobbra=6, balra=4;
    public static int isInList(ArrayList<Cells> list, Cells jelenlegi) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (jelenlegi.sor == list.get(i).sor && jelenlegi.oszlop == list.get(i).oszlop) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static Cells findPrev(ArrayList<Cells> list, Cells jelenlegi) {
        Cells elozo = list.get(0).elod;
        for (Cells cells : list) {
            if (jelenlegi.sor == cells.sor && jelenlegi.oszlop == cells.oszlop) {
                elozo = cells.elod;
                break;
            }
        }
        return elozo;
    }

    public void pozNyito(ArrayList<Cells> temp,ArrayList<Integer> iranyList,Cells cell){
        int vizsgaltSor=0;
        int vizsgaltOszlop=0;
        boolean felt=false;
        for (int i = 0; i <iranyList.size(); i++) {
            Integer irany=iranyList.get(i);
            if (irany == fel) {
                vizsgaltSor = cell.sor - 1;
                vizsgaltOszlop = cell.oszlop;
                felt = cell.sor > 0;
            } else if (irany == le) {
                vizsgaltSor = cell.sor + 1;
                vizsgaltOszlop = cell.oszlop;
                felt = cell.sor < LabirWindow.sorok - 1;
            } else if (irany == jobbra) {
                vizsgaltSor = cell.sor;
                vizsgaltOszlop = cell.oszlop + 1;
                felt = cell.oszlop < LabirWindow.oszlopok - 1;
            } else if (irany == balra) {
                vizsgaltSor = cell.sor;
                vizsgaltOszlop = cell.oszlop - 1;
                felt = cell.oszlop > 0;
            }

            if (felt && (LabirWindow.racs[vizsgaltSor][vizsgaltOszlop] != LabirWindow.FAL &&

                    isInList(LabirWindow.nyitottPoz, new Cells(vizsgaltSor, vizsgaltOszlop)) == -1 &&
                    isInList(LabirWindow.zartPoz, new Cells(vizsgaltSor, vizsgaltOszlop)) == -1)) {
                Cells cells = new Cells(vizsgaltSor, vizsgaltOszlop);
                cells.elod = jelenlegi;
                temp.add(0, cells);

            }
        }

    }

    public ArrayList<Cells> succesors_DFS(Cells jelenlegi) {
        int r = jelenlegi.sor;
        int c = jelenlegi.oszlop;

        ArrayList<Cells> temp = new ArrayList<>();

        ArrayList<Integer> iranyList=new ArrayList<>();
        iranyList.add(fel);
        iranyList.add(balra);
        iranyList.add(le);
        iranyList.add(jobbra);

        pozNyito(temp,iranyList,jelenlegi);

        return temp;
    }
    public ArrayList<Cells> succesors_DFS2(Cells jelenlegi) {
        int r = jelenlegi.sor;
        int c = jelenlegi.oszlop;

        ArrayList<Cells> temp = new ArrayList<>();
        ArrayList<Integer> iranyList=new ArrayList<>();

        iranyList.add(fel);
        iranyList.add(jobbra);
        iranyList.add(le);
        iranyList.add(balra);
        System.out.println(iranyList.get(0));
        pozNyito(temp,iranyList,jelenlegi);

        return temp;

    }
    public ArrayList<Cells> succesors_BFS(Cells jelenlegi) {
        int r = jelenlegi.sor;
        int c = jelenlegi.oszlop;

        ArrayList<Cells> temp = new ArrayList<>();
        ArrayList<Integer> iranyList=new ArrayList<>();

        iranyList.add(jobbra);
        iranyList.add(le);
        iranyList.add(balra);
        iranyList.add(fel);

        pozNyito(temp,iranyList, this.jelenlegi);


        return temp;
    }
    public ArrayList<Cells> succesors_BFS2(Cells jelenlegi) {
        int r = jelenlegi.sor;
        int c = jelenlegi.oszlop;

        ArrayList<Cells> temp = new ArrayList<>();
        ArrayList<Integer> iranyList=new ArrayList<>();

        iranyList.add(balra);
        iranyList.add(le);
        iranyList.add(jobbra);
        iranyList.add(fel);

        pozNyito(temp,iranyList, this.jelenlegi);

        return temp;
    }



    public ArrayList<Cells> succesors_aStar(Cells jelenlegi) {
        int r = jelenlegi.sor;
        int c = jelenlegi.oszlop;

        ArrayList<Cells> temp = new ArrayList<>();
        ArrayList<Integer> iranyList=new ArrayList<>();

        iranyList.add(jobbra);
        iranyList.add(le);
        iranyList.add(balra);
        iranyList.add(fel);

        pozNyito(temp,iranyList, this.jelenlegi);

            return temp;
    }


    public ArrayList<Cells> succesors_aStar2(Cells jelenlegi) {
        int r = jelenlegi.sor;
        int c = jelenlegi.oszlop;

        ArrayList<Cells> temp = new ArrayList<>();
        ArrayList<Integer> iranyList=new ArrayList<>();

        iranyList.add(balra);
        iranyList.add(le);
        iranyList.add(jobbra);
        iranyList.add(fel);

        pozNyito(temp,iranyList, this.jelenlegi);

        return temp;
    }


    synchronized public void expandNode() {
        //Cell current
        if (LabirWindow.dfs.isSelected()) {
            jelenlegi = LabirWindow.nyitottPoz.remove(0);
        }
        if (LabirWindow.dfs2.isSelected()) {
            jelenlegi = LabirWindow.nyitottPoz.remove(0);
        }
        if (LabirWindow.bfs.isSelected()) {
            jelenlegi = LabirWindow.nyitottPoz.remove(0);
        }
        if (LabirWindow.bfs2.isSelected()) {
            jelenlegi = LabirWindow.nyitottPoz.remove(0);
        }
        if (LabirWindow.aStar.isSelected()) {
            LabirWindow.nyitottPoz.sort(new LabirWindow.CellComparator());
            jelenlegi = LabirWindow.nyitottPoz.remove(0);
        }
        if (LabirWindow.aStar2.isSelected()) {
            LabirWindow.nyitottPoz.sort(new LabirWindow.CellComparator());
            jelenlegi = LabirWindow.nyitottPoz.remove(0);
        }
        System.out.println("cur:"+ jelenlegi.sor +" "+ jelenlegi.oszlop);

        LabirWindow.zartPoz.add(jelenlegi);
        LabirWindow.racs[jelenlegi.sor][jelenlegi.oszlop] = LabirWindow.ZART;
        LabirWindow.kiterjesztett++;
        ArrayList<Cells> succesors;


        if (LabirWindow.dfs.isSelected()) {
            succesors = succesors_DFS(jelenlegi);
        }
            else if (LabirWindow.dfs2.isSelected()) {
                succesors = succesors_DFS2(jelenlegi);

        } else if (LabirWindow.bfs2.isSelected()) {
            succesors = succesors_BFS2(jelenlegi);
        }else if (LabirWindow.bfs.isSelected()) {
            succesors = succesors_BFS(jelenlegi);
        }else if (LabirWindow.aStar2.isSelected()) {
            succesors = succesors_aStar2(jelenlegi);
        }
            else {
            succesors = succesors_aStar(jelenlegi);
        }

        for (Cells cells : LabirWindow.zartPoz) {
            if (cells.sor == LabirWindow.celHelye.sor && cells.oszlop == LabirWindow.celHelye.oszlop && LabirWindow.dfs.isSelected()) {
                Cells last = LabirWindow.celHelye;
                last.elod = cells.elod;
              //  MazePanel.closedSet.add(last);
                LabirWindow.megtalalt = true;
         }
        }
        for (Cells cells : LabirWindow.zartPoz) {
            if (cells.sor == LabirWindow.celHelye.sor && cells.oszlop == LabirWindow.celHelye.oszlop && LabirWindow.dfs2.isSelected()) {
                Cells last = LabirWindow.celHelye;
                last.elod = cells.elod;
                //  MazePanel.closedSet.add(last);
                LabirWindow.megtalalt = true;
            }
        }

        for (Cells cells : LabirWindow.zartPoz) {
            if (cells.sor == LabirWindow.celHelye.sor && cells.oszlop == LabirWindow.celHelye.oszlop && LabirWindow.bfs.isSelected()) {
                Cells last = LabirWindow.celHelye;
                last.elod = cells.elod;
           //     MazePanel.closedSet.add(last);
                LabirWindow.megtalalt = true;
            }
        }
        for (Cells cells : LabirWindow.zartPoz) {
            if (cells.sor == LabirWindow.celHelye.sor && cells.oszlop == LabirWindow.celHelye.oszlop && LabirWindow.bfs2.isSelected()) {
                Cells last = LabirWindow.celHelye;
                last.elod = cells.elod;
                //     MazePanel.closedSet.add(last);
                LabirWindow.megtalalt = true;
            }
        }
        for (Cells cells : LabirWindow.zartPoz) {
            if (cells.sor == LabirWindow.celHelye.sor && cells.oszlop == LabirWindow.celHelye.oszlop && LabirWindow.aStar.isSelected()) {
                Cells last = LabirWindow.celHelye;
                last.elod = cells.elod;
         //       MazePanel.closedSet.add(last);
                LabirWindow.megtalalt = true;
            }
        }
        for (Cells cells : LabirWindow.zartPoz) {
            if (cells.sor == LabirWindow.celHelye.sor && cells.oszlop == LabirWindow.celHelye.oszlop && LabirWindow.aStar2.isSelected()) {
                Cells last = LabirWindow.celHelye;
                last.elod = cells.elod;
                //       MazePanel.closedSet.add(last);
                LabirWindow.megtalalt = true;
            }
        }



        for (Cells cells : succesors) {
            System.out.println("succesorElement:"+ cells.sor +" "+ cells.oszlop);
            if (jelenlegi.sor == LabirWindow.celHelye.sor && jelenlegi.oszlop == LabirWindow.celHelye.oszlop) {
                if (LabirWindow.dfs.isSelected()) {
                    LabirWindow.nyitottPoz.add(0, cells);
                }

              if (LabirWindow.bfs.isSelected()){
                Cells last = LabirWindow.celHelye;
                last.elod = jelenlegi.elod;
                LabirWindow.zartPoz.add(last);

                LabirWindow.megtalalt = true;

                }
                if (jelenlegi.sor == LabirWindow.celHelye.sor && jelenlegi.oszlop == LabirWindow.celHelye.oszlop) {
                    if (LabirWindow.dfs2.isSelected()) {
                        LabirWindow.nyitottPoz.add(0, cells);
                    }

                    if (LabirWindow.bfs2.isSelected()){
                        Cells last = LabirWindow.celHelye;
                        last.elod = jelenlegi.elod;
                        LabirWindow.zartPoz.add(last);

                        LabirWindow.megtalalt = true;

                    }

                }
              return;
            } else {

                if (LabirWindow.dfs.isSelected()) {

                    LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                    LabirWindow.nyitottPoz.add(0, cells);
                    if (LabirWindow.racs[cells.sor][cells.oszlop] == LabirWindow.NYITOTT || LabirWindow.racs[cells.sor][cells.oszlop] == LabirWindow.CEL){
                    }
                }else if (LabirWindow.dfs2.isSelected()) {

                    LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                    LabirWindow.nyitottPoz.add(0, cells);
                    if (LabirWindow.racs[cells.sor][cells.oszlop] == LabirWindow.NYITOTT || LabirWindow.racs[cells.sor][cells.oszlop] == LabirWindow.CEL){


                    }
                }
                else if (LabirWindow.bfs.isSelected()) {
                    LabirWindow.nyitottPoz.add(cells);
                    LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                }else if (LabirWindow.bfs2.isSelected()) {
                    LabirWindow.nyitottPoz.add(cells);
                    LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                }
                else if (LabirWindow.aStar.isSelected()) {
                    cells.utvonalKoltseg = jelenlegi.utvonalKoltseg + 1;
                    cells.becsultKoltseg = Math.abs(LabirWindow.celHelye.sor - cells.sor) + Math.abs(LabirWindow.celHelye.oszlop - cells.oszlop);
                    cells.f = cells.utvonalKoltseg + cells.becsultKoltseg;
                    int openIndex = isInList(LabirWindow.nyitottPoz, cells);
                    int closedIndex = isInList(LabirWindow.zartPoz, cells);
                    if (openIndex == -1 && closedIndex == -1) {
                        LabirWindow.nyitottPoz.add(cells);
                        LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                    } else {
                        if (openIndex > -1) {
                            if (LabirWindow.nyitottPoz.get(openIndex).f > cells.f) {
                               // MazePanel.openSet.remove(openIndex);
                                LabirWindow.nyitottPoz.add(cells);
                                LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                            }
                        } else {
                            if (LabirWindow.zartPoz.get(closedIndex).f > cells.f) {
                             //   MazePanel.closedSet.remove(closedIndex);
                                LabirWindow.nyitottPoz.add(cells);
                                LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                            }
                        }
                    }
                }
                else if (LabirWindow.aStar2.isSelected()) {
                    cells.utvonalKoltseg = jelenlegi.utvonalKoltseg + 1;
                    cells.becsultKoltseg = Math.abs(LabirWindow.celHelye.sor - cells.sor) + Math.abs(LabirWindow.celHelye.oszlop - cells.oszlop);
                    cells.f = cells.utvonalKoltseg + cells.becsultKoltseg;
                    int openIndex = isInList(LabirWindow.nyitottPoz, cells);
                    int closedIndex = isInList(LabirWindow.zartPoz, cells);
                    if (openIndex == -1 && closedIndex == -1) {
                        LabirWindow.nyitottPoz.add(cells);
                        LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                    } else {
                        if (openIndex > -1) {
                            if (LabirWindow.nyitottPoz.get(openIndex).f > cells.f) {
                                // MazePanel.openSet.remove(openIndex);
                                LabirWindow.nyitottPoz.add(cells);
                                LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                            }
                        } else {
                            if (LabirWindow.zartPoz.get(closedIndex).f > cells.f) {
                                //   MazePanel.closedSet.remove(closedIndex);
                                LabirWindow.nyitottPoz.add(cells);
                                LabirWindow.racs[cells.sor][cells.oszlop] = LabirWindow.NYITOTT;
                            }
                        }
                    }
                }
            }

            /*System.out.println("\nZárt szám " + jelenlegi.sor + "  " + jelenlegi.oszlop);

            System.out.println(" jelenlegi: " + jelenlegi.sor + " " + jelenlegi.oszlop);
            System.out.println("célhely: " + LabirWindow.celHelye.sor + " " + LabirWindow.celHelye.oszlop);
            System.out.println("\n Nyitott szám: " + cells.sor + "  " + cells.oszlop);
            LabirWindow.nyitottPoz.forEach(Cell -> {
                System.out.println("Nyitott állapotok halmaza:" + (Cell.sor) + " " + (Cell.oszlop));
            });
            //System.out.println("" + LabirWindow.stringMaker());*/
        }
        /*LabirWindow.zartPoz.forEach(Cell -> {
            System.out.println("Zárt állapotok halmaza: " + (Cell.sor) + " " + (Cell.oszlop));});*/
    }


    public void plotRoute() {

        LabirWindow.kereses = false;
        LabirWindow.keresesVege = true;

        int lepesek = 0;
        Cells cur = LabirWindow.celHelye;
        LabirWindow.racs[cur.sor][cur.oszlop] = LabirWindow.CEL;
        do {
            lepesek++;
            cur = findPrev(LabirWindow.zartPoz, cur);
            LabirWindow.racs[cur.sor][cur.oszlop] = LabirWindow.UT;
        } while (cur != LabirWindow.kezdesHelye);
        LabirWindow.info.setText("Kiterjesztett Lépés: " + LabirWindow.kiterjesztett + " csomópont. Szükség szintek " + lepesek + " ");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        if (cmd.equals("Törlés")) {
            fillGrid();
            LabirWindow.dfs.setEnabled(true);
            LabirWindow.dfs2.setEnabled(true);
            LabirWindow.bfs.setEnabled(true);
            LabirWindow.bfs2.setEnabled(true);
            LabirWindow.aStar.setEnabled(true);
            LabirWindow.aStar2.setEnabled(true);
        } else if (cmd.equals("Lépésenként") && !LabirWindow.megtalalt && !LabirWindow.keresesVege) {
            LabirWindow.kereses = true;
            LabirWindow.info.setText("Választjuk ki a Lépésenként vagy Automatikus futatást");
            LabirWindow.dfs.setEnabled(false);
            LabirWindow.dfs2.setEnabled(false);
            LabirWindow.bfs.setEnabled(false);
            LabirWindow.bfs2.setEnabled(false);
            LabirWindow.aStar.setEnabled(false);
            LabirWindow.aStar2.setEnabled(false);
            LabirWindow.ido.stop();

            System.out.println(LabirWindow.nyitottPoz.isEmpty());

            if (LabirWindow.nyitottPoz.isEmpty()) {
                LabirWindow.keresesVege = true;
                LabirWindow.info.setText("Nincs út a célhoz!");
            } else {
                expandNode();

                if (LabirWindow.megtalalt) {

                    LabirWindow.keresesVege = true;
                    plotRoute();
                }
            }
            repaint();
        } else if (cmd.equals("Automatikus") && !LabirWindow.keresesVege) {
            LabirWindow.kereses = true;
            LabirWindow.info.setText("Válassza a Lépésenként,Automatikus  vagy a Törlés lehetőséget");
            LabirWindow.dfs.setEnabled(false);
            LabirWindow.dfs2.setEnabled(false);
            LabirWindow.bfs.setEnabled(false);
            LabirWindow.bfs2.setEnabled(false);
            LabirWindow.aStar2.setEnabled(false);
            LabirWindow.aStar.setEnabled(false);
            LabirWindow.ido.setDelay(LabirWindow.kesleltetes);
            LabirWindow.ido.start();
        }
    }
}