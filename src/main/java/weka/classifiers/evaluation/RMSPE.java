/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * RMSPE.java
 * Copyright (C) 2021 University of Waikato, Hamilton, NZ
 */

package weka.classifiers.evaluation;

import weka.core.Instance;
import weka.core.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Root Mean Square Percentage Error (RMSPE).
 *
 * See here: <a href="https://www.kaggle.com/c/rossmann-store-sales/overview/evaluation">https://www.kaggle.com/c/rossmann-store-sales/overview/evaluation</a>
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class RMSPE
  extends AbstractEvaluationMetric
  implements StandardEvaluationMetric {

  private static final long serialVersionUID = 7535615045890586403L;

  public final static String NAME = "RMSPE";

  /** the collected actual. */
  protected List<Double> m_Actual = new ArrayList<Double>();

  /** the collected predicted. */
  protected List<Double> m_Predicted = new ArrayList<Double>();

  /**
   * Return true if this evaluation metric can be computed when the class is
   * nominal
   *
   * @return true if this evaluation metric can be computed when the class is
   * nominal
   */
  @Override
  public boolean appliesToNominalClass() {
    return false;
  }

  /**
   * Return true if this evaluation metric can be computed when the class is
   * numeric
   *
   * @return true if this evaluation metric can be computed when the class is
   * numeric
   */
  @Override
  public boolean appliesToNumericClass() {
    return true;
  }

  /**
   * Get the name of this metric
   *
   * @return the name of this metric
   */
  @Override
  public String getMetricName() {
    return NAME;
  }

  /**
   * Get a short description of this metric (algorithm, forumulas etc.).
   *
   * @return a short description of this metric
   */
  @Override
  public String getMetricDescription() {
    return "Root Mean Square Percentage Error";
  }

  /**
   * Get a list of the names of the statistics that this metrics computes. E.g.
   * an information theoretic evaluation measure might compute total number of
   * bits as well as average bits/instance
   *
   * @return the names of the statistics that this metric computes
   */
  @Override
  public List<String> getStatisticNames() {
    return Arrays.asList(NAME);
  }

  /**
   * Get the value of the named statistic
   *
   * @param statName the name of the statistic to compute the value for
   * @return the computed statistic or Utils.missingValue() if the statistic
   * can't be computed for some reason
   */
  @Override
  public double getStatistic(String statName) {
    double	result;
    double	val;
    int		i;

    if (!statName.equals(NAME))
      return Utils.missingValue();
    if (m_Actual.size() == 0)
      return Utils.missingValue();

    result = 0.0;
    for (i = 0; i < m_Actual.size(); i++) {
      val = (m_Actual.get(i) - m_Predicted.get(i)) / m_Actual.get(i);
      result += val * val;
    }
    result /= m_Actual.size();
    result = Math.sqrt(result);

    return result;
  }

  /**
   * Return a formatted string (suitable for displaying in console or GUI
   * output) containing all the statistics that this metric computes.
   *
   * @return a formatted string containing all the computed statistics
   */
  @Override
  public String toSummaryString() {
    double	bias;
    int		width;

    bias = getStatistic(NAME);
    width = 41;
    if (bias >= 1.0)
      width -= Integer.toString((int) bias).length() + 1;
    return Utils.padRight(NAME, width) + Utils.doubleToString(bias, 4) + "\n";
  }

  /**
   * Updates the statistics about a classifiers performance for the current test
   * instance. Gets called when the class is nominal. Implementers need only
   * implement this method if it is not possible to compute their statistics
   * from what is stored in the base Evaluation object.
   *
   * @param predictedDistribution the probabilities assigned to each class
   * @param instance              the instance to be classified
   * @throws Exception if the class of the instance is not set
   */
  @Override
  public void updateStatsForClassifier(double[] predictedDistribution, Instance instance) throws Exception {
    // ignored
  }

  /**
   * Updates the statistics about a predictors performance for the current test
   * instance. Gets called when the class is numeric. Implementers need only
   * implement this method if it is not possible to compute their statistics
   * from what is stored in the base Evaluation object.
   *
   * @param predictedValue the numeric value the classifier predicts
   * @param instance       the instance to be classified
   * @throws Exception if the class of the instance is not set
   */
  @Override
  public void updateStatsForPredictor(double predictedValue, Instance instance) throws Exception {
    if (!instance.classIsMissing() && !Utils.isMissingValue(predictedValue)) {
      m_Actual.add(instance.classValue());
      m_Predicted.add(predictedValue);
    }
  }
}
