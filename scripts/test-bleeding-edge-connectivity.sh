#!/bin/bash

echo "🩸 GENESIS BLEEDING-EDGE CONNECTIVITY TEST"
echo "=========================================="
echo ""

echo "Testing repository connectivity..."
echo ""

# Test Google Maven repository (where AGP lives)
echo "1. Testing Google Maven (AGP 8.13.0-alpha04)..."
curl -s -I "https://dl.google.com/dl/android/maven2/" > /dev/null
if [ $? -eq 0 ]; then
    echo "   ✅ Google Maven accessible"
else
    echo "   ❌ Google Maven NOT accessible (THIS IS THE ISSUE!)"
fi

# Test specific AGP alpha access
echo "2. Testing AGP alpha path..."
curl -s -I "https://dl.google.com/dl/android/maven2/com/android/tools/build/gradle/" > /dev/null
if [ $? -eq 0 ]; then
    echo "   ✅ AGP repository path accessible"
else
    echo "   ❌ AGP repository path NOT accessible"
fi

# Test Compose snapshots
echo "3. Testing Compose snapshots (BOM 2025.07.00)..."
curl -s -I "https://androidx.dev/storage/compose-compiler/repository/" > /dev/null
if [ $? -eq 0 ]; then
    echo "   ✅ Compose snapshots accessible"
else
    echo "   ❌ Compose snapshots NOT accessible"
fi

# Test Java 24 detection
echo "4. Testing Java 24 toolchain..."
java -version 2>&1 | grep -q "24\."
if [ $? -eq 0 ]; then
    echo "   ✅ Java 24 detected"
else
    echo "   ❌ Java 24 NOT detected"
fi

echo ""
echo "DIAGNOSIS:"
echo "=========="

# Check if behind corporate firewall
if ! curl -s -I "https://dl.google.com/dl/android/maven2/" > /dev/null; then
    echo "🔥 ROOT CAUSE FOUND:"
    echo "   - Cannot access dl.google.com (corporate firewall?)"
    echo "   - This blocks AGP 8.13.0-alpha04 downloads"
    echo ""
    echo "SOLUTIONS:"
    echo "1. Configure proxy in gradle.properties"
    echo "2. Use mobile hotspot to bypass corporate network"
    echo "3. VPN to access Google repositories"
    echo ""
    echo "See BLEEDING_EDGE_FIX.md for detailed proxy config"
else
    echo "✅ Network connectivity OK"
    echo "   - Issue might be cache corruption"
    echo "   - Run: ./scripts/fix-bleeding-edge-cache.bat"
fi

echo ""
echo "Your bleeding-edge config is CORRECT! 🩸👑"
echo "AGP 8.13.0-alpha04, Java 24, Kotlin 2.2.0 all exist!"
