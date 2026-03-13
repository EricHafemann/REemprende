package org.reempreende.infrastructure.utility;

import java.util.HashMap;
import java.util.Map;

public class TextoUtil {
    private static final Map<Character, String> map = new HashMap<>();

    static {

        // maiúsculas
        map.put('A',"𝓐"); map.put('B',"𝓑"); map.put('C',"𝓒"); map.put('D',"𝓓");
        map.put('E',"𝓔"); map.put('F',"𝓕"); map.put('G',"𝓖"); map.put('H',"𝓗");
        map.put('I',"𝓘"); map.put('J',"𝓙"); map.put('K',"𝓚"); map.put('L',"𝓛");
        map.put('M',"𝓜"); map.put('N',"𝓝"); map.put('O',"𝓞"); map.put('P',"𝓟");
        map.put('Q',"𝓠"); map.put('R',"𝓡"); map.put('S',"𝓢"); map.put('T',"𝓣");
        map.put('U',"𝓤"); map.put('V',"𝓥"); map.put('W',"𝓦"); map.put('X',"𝓧");
        map.put('Y',"𝓨"); map.put('Z',"𝓩");

        // minúsculas
        map.put('a',"𝓪"); map.put('b',"𝓫"); map.put('c',"𝓬"); map.put('d',"𝓭");
        map.put('e',"𝓮"); map.put('f',"𝓯"); map.put('g',"𝓰"); map.put('h',"𝓱");
        map.put('i',"𝓲"); map.put('j',"𝓳"); map.put('k',"𝓴"); map.put('l',"𝓵");
        map.put('m',"𝓶"); map.put('n',"𝓷"); map.put('o',"𝓸"); map.put('p',"𝓹");
        map.put('q',"𝓺"); map.put('r',"𝓻"); map.put('s',"𝓼"); map.put('t',"𝓽");
        map.put('u',"𝓾"); map.put('v',"𝓿"); map.put('w',"𝔀"); map.put('x',"𝔁");
        map.put('y',"𝔂"); map.put('z',"𝔃");
    }

    public static String transformar(String texto) {

        StringBuilder sb = new StringBuilder();

        for (char c : texto.toCharArray()) {

            if(map.containsKey(c))
                sb.append(map.get(c));
            else
                sb.append(c);
        }

        return sb.toString();
    }
}
