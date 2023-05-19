/*
 * DIVA Android - Damn Insecure and Vulnerable App for Android
 *
 * Copyright 2016 © Payatu
 * Author: Aseem Jakhar aseem[at]payatu[dot]com
 * Websites: www.payatu.com  www.nullcon.net  www.hardwear.io www.null.co.in
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package jakhar.aseem.diva;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startChallenge(View view) {

        if (view == findViewById(R.id.d1button)) {
            Intent i = new Intent(this, LogActivity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d2button)) {
            Intent i = new Intent(this, HardcodeActivity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d3button)) {
            Intent i = new Intent(this, InsecureDataStorage1Activity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d4button)) {
            Intent i = new Intent(this, InsecureDataStorage2Activity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d5button)) {
            Intent i = new Intent(this, InsecureDataStorage3Activity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d6button)) {
            Intent i = new Intent(this, InsecureDataStorage4Activity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d7button)) {
            Intent i = new Intent(this, SQLInjectionActivity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d8button)) {
            Intent i = new Intent(this, InputValidation2URISchemeActivity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d9button)) {
            Intent i = new Intent(this, AccessControl1Activity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d10button)) {
            Intent i = new Intent(this, AccessControl2Activity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d11button)) {
            Intent i = new Intent(this, AccessControl3Activity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d12button)) {
            Intent i = new Intent(this, Hardcode2Activity.class);
            startActivity(i);
        }
        else if (view == findViewById(R.id.d13button)) {
            Intent i = new Intent(this, InputValidation3Activity.class);
            startActivity(i);
        }
    }
}
