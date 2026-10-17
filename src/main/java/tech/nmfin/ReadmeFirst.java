/*
 * Copyright (c) NM LTD.
 * https://nm.dev/
 * 
 * THIS SOFTWARE IS LICENSED, NOT SOLD.
 * 
 * YOU MAY USE THIS SOFTWARE ONLY AS DESCRIBED IN THE LICENSE.
 * IF YOU ARE NOT AWARE OF AND/OR DO NOT AGREE TO THE TERMS OF THE LICENSE,
 * DO NOT USE THIS SOFTWARE.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITH NO WARRANTY WHATSOEVER,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, WITHOUT LIMITATION,
 * ANY WARRANTIES OF ACCURACY, ACCESSIBILITY, COMPLETENESS,
 * FITNESS FOR A PARTICULAR PURPOSE, MERCHANTABILITY, NON-INFRINGEMENT, 
 * TITLE AND USEFULNESS.
 * 
 * IN NO EVENT AND UNDER NO LEGAL THEORY,
 * WHETHER IN ACTION, CONTRACT, NEGLIGENCE, TORT, OR OTHERWISE,
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIMS, DAMAGES OR OTHER LIABILITIES,
 * ARISING AS A RESULT OF USING OR OTHER DEALINGS IN THE SOFTWARE.
 */
package tech.nmfin;

import java.io.File;
import dev.nm.misc.license.License;
import dev.nm.algebra.linear.matrix.doubles.Matrix;
import dev.nm.algebra.linear.matrix.doubles.matrixtype.dense.DenseMatrix;
import tech.nmfin.algoquant.execution.datatype.StockEOD;

/** A runnable example using AlgoQuant and the NM Dev bundled inside its JAR. */
public class ReadmeFirst {
    public static void main(String[] args) {
        File license = new File(args.length == 0 ? "license/nm.lic" : args[0]);
        if (!license.isFile()) {
            throw new IllegalArgumentException("NM license file not found: " + license.getAbsolutePath());
        }
        License.setLicenseFile(license);
        ReadmeFirst example = new ReadmeFirst();
        example.run_0010();
        example.run_0020();
        example.run_0030();
        System.out.println("AlgoQuant example completed successfully.");
    }

    public void run_0010() {
        System.out.println("Hello AlgoQuant!");
    }

    public void run_0020() {
        Matrix a = new DenseMatrix(new double[][]{{1, 2}, {3, 4}});
        Matrix b = new DenseMatrix(new double[][]{{1, 3}, {2, 4}});
        Matrix product = a.multiply(b);
        double[][] expected = {{5, 11}, {11, 25}};
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                if (product.get(i, j) != expected[i - 1][j - 1]) {
                    throw new AssertionError("Unexpected matrix product");
                }
            }
        }
        System.out.println("NM Dev matrix product:");
        System.out.println(product);
    }

    public void run_0030() {
        StockEOD bar = new StockEOD(10, 12, 9, 11, 12345, 10.5);
        if (bar.open() != 10 || bar.high() != 12 || bar.low() != 9
                || bar.close() != 11 || bar.volume() != 12345 || bar.adjClose() != 10.5) {
            throw new AssertionError("Unexpected AlgoQuant end-of-day price");
        }
        System.out.println("AlgoQuant end-of-day price: " + bar);
    }
}
