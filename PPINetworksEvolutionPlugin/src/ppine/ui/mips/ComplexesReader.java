/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.ui.mips;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ComplexesReader {

    private Map<String, Complex> complexes = new TreeMap<String, Complex>();
    private Collection<String> complexesNames;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;

    public ComplexesReader(Collection<String> complexesNames) {
        this.complexesNames = complexesNames;
        createComplexes(complexesNames);
    }

    public Map<String, Complex> readComplexes(File file) throws IOException {

        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        br = new BufferedReader(new InputStreamReader(dis));

        while (br.ready()) {
            String line = br.readLine();
            line = line.toUpperCase();
            String[] tokens = line.split("[|]");

            if (tokens.length >= 2) {
                //    System.out.println("Analysing: " + tokens[0] + " " + tokens[1]);
                for (String name : complexesNames) {
                    if (tokens[1].contains(name)) {
                        Complex complex = complexes.get(name);
                        complex.addProtein(tokens[0]);
                    }
                }
            }
        }

        br.close();
        dis.close();
        bis.close();
        fis.close();
        return complexes;
    }

    public Map<String, Complex> getComplexes() {
        return complexes;
    }

    private void createComplexes(Collection<String> complexesNames) {
        for (String name : complexesNames) {
            Complex complex = new Complex(name);
            complexes.put(name, complex);
        }
    }
}
