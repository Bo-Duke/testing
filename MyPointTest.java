import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyPointTest {

    @Mock
    private Random random1;

    @Mock
    private Random random2;

    @Mock
    private ITranslation translation;

    private MyPoint point;

    @org.junit.Before
    public void setUp() throws Exception {
        this.point = new MyPoint();
    }

    @org.junit.Test
    public void myPoint() throws Exception {
        MyPoint point = new MyPoint();
        assertEquals(0d, point.getX(), 0.0001);
        assertEquals(0d, point.getY(), 0.0001);
    }

    @org.junit.Test
    public void myPointDoubleDouble() throws Exception {
        double myX = 3d;
        double myY = 4d;
        MyPoint point = new MyPoint(myX, myY);
        assertEquals(myX, point.getX(), 0.0001);
        assertEquals(myY, point.getY(), 0.0001);
    }

    @org.junit.Test
    public void myPointMyPoint() throws Exception {
        double myX = 12d;
        double myY = 15d;
        point.setX(myX);
        point.setY(myY);
        MyPoint secondPoint = new MyPoint(point);
        assertEquals(myX, secondPoint.getX(), 0.0001);
        assertEquals(myY, secondPoint.getY(), 0.0001);
    }

    @org.junit.Test
    public void myPointMyPoint2() throws Exception {
        MyPoint secondPoint = new MyPoint(point);
        assertEquals(0d, secondPoint.getX(), 0.0001);
        assertEquals(0d, secondPoint.getY(), 0.0001);
    }

    @org.junit.Test
    public void myPointMyPointNull() throws Exception {
        MyPoint secondPoint = new MyPoint(null);
        assertEquals(0d, secondPoint.getX(), 0.0001);
        assertEquals(0d, secondPoint.getY(), 0.0001);
    }

    @org.junit.Test
    public void setGetX() throws Exception {
        double myX = 10d;
        this.point.setX(myX);
        assertEquals(myX, point.getX(), 0.0001);
    }

    @org.junit.Test
    public void setGetY() throws Exception {
        double myY = 5d;
        this.point.setY(myY);
        assertEquals(myY, point.getY(), 0.0001);
    }

    @Test
    public void getXNaN() throws Exception {
        this.point.setX(Double.NaN);
        assertNotEquals(Double.NaN, point.getX(), 0.0001);
    }

    @Test
    public void getYNaN() throws Exception {
        this.point.setY(Double.NaN);
        assertNotEquals(Double.NaN, point.getY(), 0.0001);
    }

    @org.junit.Test
    public void scale() throws Exception {
        double myX = 2d;
        double myY = 2d;
        this.point.setX(myX);
        this.point.setY(myY);
        MyPoint scaledPoint = this.point.scale(2);
        assertEquals(myX*2, scaledPoint.getX(), 0.0001);
        assertEquals(myY*2, scaledPoint.getX(), 0.0001);
    }

    @org.junit.Test
    public void scaleNegative() throws Exception {
        double myX = 2d;
        double myY = 2d;
        this.point.setX(myX);
        this.point.setY(myY);
        MyPoint scaledPoint = this.point.scale(-2);
        assertEquals(myX*-2, scaledPoint.getX(), 0.0001);
        assertEquals(myY*-2, scaledPoint.getX(), 0.0001);
    }

    @org.junit.Test
    public void horizontalSymmetry() throws Exception {
        double myX = 2d;
        double myY = 2d;
        this.point.setX(myX);
        this.point.setY(myY);
        MyPoint newPoint = this.point.horizontalSymmetry(new MyPoint());
        assertEquals(2d * 0 - myX, newPoint.getX(), 0.0001);
        assertEquals(myY, newPoint.getY(), 0.0001);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void horizontalSymmetryNull() throws Exception {
        this.point.horizontalSymmetry(null);
    }

    @Test
    public void computeAngle() throws Exception {
        double angle = this.point.computeAngle(new MyPoint(1d,0d));
        assertEquals(0, angle, 0.001);
    }

    @Test
    public void rotatePointNull() throws Exception {
        MyPoint rotated = this.point.rotatePoint(null, 2d);
        assertEquals(null, rotated);
    }

    @Test
    public void centralSymmetry() throws Exception {
        this.point.setX(1d);
        this.point.setY(1d);
        MyPoint symetric = this.point.centralSymmetry(new MyPoint());
        assertEquals(-1d, symetric.getX(), 0.0001);
        assertEquals(-1d, symetric.getY(), 0.0001);
    }

    @Test
    public void getMiddlePoint() throws Exception {
        MyPoint middle = this.point.getMiddlePoint(new MyPoint(2,0));
        assertEquals(1, middle.getX(), 0.0001);
        assertEquals(0, middle.getY(), 0.0001);
    }

    @Test
    public void translate() throws Exception {
        this.point.translate(1, 2);
        assertEquals(1, this.point.getX(), 0.0001);
        assertEquals(2, this.point.getY(), 0.0001);
    }

    @Test
    public void translateMock() throws Exception {
        when(this.translation.getTx()).thenReturn(1);
        when(this.translation.getTy()).thenReturn(2);
        this.point.translate(this.translation);
        assertEquals(1, this.point.getX(), 0.0001);
        assertEquals(2, this.point.getY(), 0.0001);
    }

    @Test
    public void setPoint() throws Exception {
        this.point.setPoint(new Random(1), new Random(1));
        assertTrue(this.point.getX() < 1);
        assertTrue(this.point.getY() < 1);
    }

    @Test
    public void setPointMock() throws Exception {
        int randomX = 2;
        int randomY = 4;
        when(random1.nextInt()).thenReturn(randomX);
        when(random2.nextInt()).thenReturn(randomY);
        this.point.setPoint(random1, random2);
        assertEquals(randomX, this.point.getX(), 0.0001);
        assertEquals(randomY, this.point.getY(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCentralSymmetryNULL ( ) {
        new MyPoint(10, 20).centralSymmetry(null);
    }
}